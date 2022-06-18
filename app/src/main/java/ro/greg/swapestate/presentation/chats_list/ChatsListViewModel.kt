package ro.greg.swapestate.presentation.chats_list



import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.tasks.await
import ro.greg.swapestate.domain.model.Chat
import ro.greg.swapestate.domain.model.Rental
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.model.User
import ro.greg.swapestate.domain.use_case.auth_use_cases.AuthUseCases
import ro.greg.swapestate.domain.use_case.cloud_storage_use_cases.CloudStorageUseCases
import ro.greg.swapestate.domain.use_case.firestore_use_cases.FirestoreUseCases
import javax.inject.Inject

@HiltViewModel
class ChatsListViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val firestoreUseCases: FirestoreUseCases,
    private val cloudStorageUseCases: CloudStorageUseCases
): ViewModel() {

    val imagesRef = FirebaseStorage.getInstance().getReference("images")


    private val _chatsState = mutableStateOf<Response<List<Chat>>>(Response.Loading)
    val chatsState: State<Response<List<Chat>>> = _chatsState


    private val _userInfoState = mutableStateOf<Response<User?>>(Response.Loading)
    val userInfoState: State<Response<User?>> = _userInfoState


    private val userUid get() = authUseCases.getUserUid()


    init {
        val userResp = FirebaseFirestore.getInstance().collection("users")
            .document(userUid).addSnapshotListener { snapshot, e ->
                val user = snapshot!!.toObject(User::class.java)
                getChatsList(user!!)
            }

    }

    fun getUserId(chat: Chat): String? {
        val id = chat.userList?.filter { s -> s != userUid }?.single()
        return id
    }


    private fun getChatsList(user: User) {
        viewModelScope.launch {
            firestoreUseCases.getChats(user).collect { response ->
                _chatsState.value = response
            }
        }
    }

//    fun getProfileImageUrl(imageUserId: String) {
//         val _getProfileImageUrlState = mutableStateOf<Response<String>>(Response.Success(""))
//        val getProfileImageUrlState: State<Response<String>> = _getProfileImageUrlState
//        viewModelScope.launch {
//
//            cloudStorageUseCases.getImageUrl(imageUserId).collect { response ->
//                _getProfileImageUrlState.value = response
//
//            }
//
//
//        }
fun cloudStorageGetImageUrl(fileName: String): String = runBlocking {
    imagesRef.child(fileName).downloadUrl.await().toString()
}


    fun getChatCard(ownerId: String, rentalId: String): State<Response<HashMap<String, String?>>> {
        val _getChatCardState = mutableStateOf<Response<HashMap<String, String?>>>(Response.Loading)
        val getChatCardState: State<Response<HashMap<String, String?>>> = _getChatCardState
        viewModelScope.launch {

            firestoreUseCases.getChatCard(ownerId, rentalId).collect { response ->
                _getChatCardState.value = response
            }
//    }

        }
        return getChatCardState
    }










}


