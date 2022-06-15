package ro.greg.swapestate.presentation.chat

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.domain.model.Chat
import ro.greg.swapestate.domain.model.Rental
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.model.User
import ro.greg.swapestate.domain.use_case.auth_use_cases.AuthUseCases
import ro.greg.swapestate.domain.use_case.cloud_storage_use_cases.CloudStorageUseCases
import ro.greg.swapestate.domain.use_case.firestore_use_cases.FirestoreUseCases
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject
constructor(
    private val authUseCases: AuthUseCases,
    private val firestoreUseCases: FirestoreUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _userInfoState = mutableStateOf<Response<User?>>(Response.Loading)
    val userInfoState: State<Response<User?>> = _userInfoState

    private val _getChatState = mutableStateOf<Response<Rental?>>(Response.Loading)
    val getChatState: State<Response<Rental?>> = _getChatState


    init {
        savedStateHandle.get<String>(Constants.PARAM_CHAT_ID)?.let{
                chatId ->
            getChat(chatId)
        }
    }
//
//     fun getChat(chatId: String): Rental {
//         val rental: Rental
//         runBlocking {
//             val chatResp =  FirebaseFirestore.getInstance().collection("chats")
//                 .document(chatId).get().await().toObject(Chat::class.java)
//             rental = FirebaseFirestore.getInstance().collection("rentals")
//                 .document(chatResp!!.rentalId!!).get().await().toObject(Rental::class.java)!!
//         }
//         return rental
//    }



     fun getChat(chatId: String) {
         viewModelScope.launch {
             firestoreUseCases.getChat(chatId).collect { response ->
                 _getChatState.value = response
             }
         }
     }


    fun getUserInfo(userUid:String){
        viewModelScope.launch {
            firestoreUseCases.getUserInfo(userUid).collect { response ->
                _userInfoState.value = response
            }
        }
    }
}

