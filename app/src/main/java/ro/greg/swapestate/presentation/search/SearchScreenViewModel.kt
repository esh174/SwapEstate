package ro.greg.swapestate.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import ro.greg.swapestate.domain.model.Rental
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.model.User
import ro.greg.swapestate.domain.use_case.auth_use_cases.AuthUseCases
import ro.greg.swapestate.domain.use_case.cloud_storage_use_cases.CloudStorageUseCases
import ro.greg.swapestate.domain.use_case.firestore_use_cases.FirestoreUseCases
import javax.inject.Inject


@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val firestoreUseCases: FirestoreUseCases,
    private val cloudStorageUseCases: CloudStorageUseCases
) : ViewModel() {
    private val _getProfileImageUrlState = mutableStateOf<Response<String>>(Response.Success(""))
    val getProfileImageUrlState: State<Response<String>> = _getProfileImageUrlState

    private var _getRentalImagesUrlState = mutableStateOf<Response<List<String>>>(Response.Loading)
    val getRentalImagesUrlState: State<Response<List<String>>> = _getRentalImagesUrlState

    private val _userInfoState = mutableStateOf<Response<User?>>(Response.Loading)
    val userInfoState: State<Response<User?>> = _userInfoState

    private val _rentalsState = mutableStateOf<Response<List<Rental>>>(Response.Loading)
    val rentalsState: State<Response<List<Rental>>> = _rentalsState

    private val currentUserId get() = authUseCases.getUserUid()

    init {
        getRentalsQuery()
    }

    fun getProfileImageUrl(userUid: String) {
        viewModelScope.launch {
            cloudStorageUseCases.getImageUrl(userUid).collect { response ->
                _getProfileImageUrlState.value = response
            }
        }
    }

    fun getRentalImagesUrl(rentalId: String, count: Int) {
        viewModelScope.launch {
            cloudStorageUseCases.getSeveralImages(rentalId, count).collect { response ->
                _getRentalImagesUrlState.value = response
            }
        }
    }

    fun getRentalsQuery() {
        viewModelScope.launch {
            firestoreUseCases.getRentals().collect { response ->
                _rentalsState.value = response
            }
        }
    }

    fun getUserInfo(userUid: String) {
        viewModelScope.launch {
            firestoreUseCases.getUserInfo(userUid).collect { response ->
                _userInfoState.value = response
            }
        }
    }

    fun getChatId(rental: Rental): String {
        val result: String
        runBlocking {
            val chat = FirebaseFirestore.getInstance().collection("chats")
                .whereEqualTo("rentalId", rental.id)
                .whereArrayContains("userList", currentUserId).get().await()
            if (chat.isEmpty) {
                val chatId = FirebaseFirestore.getInstance().collection("chats")
                    .document().id
                val chat1 = hashMapOf(
                    "id" to chatId,
                    "userList" to arrayListOf(currentUserId, rental.userId!!),
                    "rentalId" to rental.id,
                )
                FirebaseFirestore.getInstance().collection("chats").document(chatId).set(chat1)
                FirebaseFirestore.getInstance().collection("users")
                    .document(currentUserId)
                    .update("chatsList", FieldValue.arrayUnion(chatId))
                FirebaseFirestore.getInstance().collection("users")
                    .document(rental.userId!!)
                    .update("chatsList", FieldValue.arrayUnion(chatId))
                result = chatId
            } else {
                result = chat.documents[0].data!!.get("id") as String
            }
        }
        return result
    }
}


















