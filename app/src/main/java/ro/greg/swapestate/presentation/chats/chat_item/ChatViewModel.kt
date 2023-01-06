package ro.greg.swapestate.presentation.chats.chat_item

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.domain.model.*
import ro.greg.swapestate.domain.use_case.auth_use_cases.AuthUseCases
import ro.greg.swapestate.domain.use_case.cloud_storage_use_cases.CloudStorageUseCases
import ro.greg.swapestate.domain.use_case.firestore_use_cases.FirestoreUseCases
import java.util.*
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject
constructor(
    private val authUseCases: AuthUseCases,
    private val firestoreUseCases: FirestoreUseCases,
    private val cloudStorageUseCases: CloudStorageUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val userUid get() = authUseCases.getUserUid()



    private val _userInfoState = mutableStateOf<Response<User?>>(Response.Loading)
    val userInfoState: State<Response<User?>> = _userInfoState

    private val _getChatState = mutableStateOf<Response<Chat?>>(Response.Loading)
    val getChatState: State<Response<Chat?>> = _getChatState

    private val _getProfileImageUrlState = mutableStateOf<Response<String>>(Response.Success(""))
    val getProfileImageUrlState: State<Response<String>> = _getProfileImageUrlState


    private val _messagesState = mutableStateOf<Response<List<Message>>>(Response.Loading)
    val messagesState: State<Response<List<Message>>> = _messagesState


    private val _messageAddState = mutableStateOf<Response<Void?>>(Response.Success(null))
    val messageAddState: State<Response<Void?>> = _messageAddState


    val currentChatId = savedStateHandle.get<String>(Constants.PARAM_CHAT_ID)
    init {
        savedStateHandle.get<String>(Constants.PARAM_CHAT_ID)?.let{
                chatId ->
            getChat(chatId)
            getMessagesList(chatId)
        }
    }


     fun getChat(chatId: String) {
         viewModelScope.launch {
             firestoreUseCases.getChat(chatId).collect { response ->
                 _getChatState.value = response
             }
         }
     }

    private fun getMessagesList(chatId: String) {
        viewModelScope.launch {
            firestoreUseCases.getMessages(chatId).collect { response ->
                _messagesState.value = response
            }
        }
    }

     fun addMessage(text: String) {
         val calendar = Calendar.getInstance()
         val day = if (calendar.get(Calendar.DATE) < 10) {
             "0${calendar.get(Calendar.DATE)}"
         } else "${calendar.get(Calendar.DATE)}"

         val month = if (calendar.get(Calendar.MONTH) < 10) {
             "0${calendar.get(Calendar.MONTH)+1}"
         } else "${calendar.get(Calendar.MONTH)+1}"

         val year = if (calendar.get(Calendar.YEAR) < 10) {
             "0${calendar.get(Calendar.YEAR)}"
         } else "${calendar.get(Calendar.YEAR)}"

         val hours = if (calendar.get(Calendar.HOUR_OF_DAY) < 10) {
             "0${calendar.get(Calendar.HOUR_OF_DAY)}"
         } else "${calendar.get(Calendar.HOUR_OF_DAY)}"

         val minutes = if (calendar.get(Calendar.MINUTE) < 10) {
             "0${calendar.get(Calendar.MINUTE)}"
         } else "${calendar.get(Calendar.MINUTE)}"

         val seconds = if (calendar.get(Calendar.SECOND) < 10) {
             "0${calendar.get(Calendar.SECOND)}"
         } else "${calendar.get(Calendar.SECOND)}"

        val message =  Message(
                text =  text,
                sentBy = userUid,
                sentOn = "${hours}:${minutes}:${seconds} ${day}.${month}.${year}"


            )

        viewModelScope.launch {
            currentChatId?.let {
                firestoreUseCases.addMessage(message = message, chatId = it).collect { response ->
                    _messageAddState.value = response
                }
            }
        }
    }


    fun getUserId(chat: Chat): String? {
        val id = chat.userList?.filter { s -> s != userUid }?.single()
        return id
    }

    fun isCurrentUser(userId: String): Boolean {
        return userUid == userId
    }

    fun getProfileImageUrl(userUid: String){
        viewModelScope.launch {
            cloudStorageUseCases.getImageUrl(userUid).collect { response ->
                _getProfileImageUrlState.value = response
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

