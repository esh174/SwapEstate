package ro.greg.swapestate.presentation.user_details

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.use_case.CloudStorageUseCases
import ro.greg.swapestate.domain.use_case.auth_use_cases.AuthUseCases
import ro.greg.swapestate.domain.use_case.firestore_use_cases.FirestoreUseCases
import javax.inject.Inject


@HiltViewModel
class UserDetailsViewModel @Inject
constructor(
    private val authUseCases: AuthUseCases,
    private val firestoreUseCases: FirestoreUseCases,
    private val cloudStorageUseCases: CloudStorageUseCases
): ViewModel() {
    private val _imageAddState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val imageAddState: State<Response<Boolean>> = _imageAddState


    private val userUid get() = authUseCases.getUserUid()

    fun addUserInfo(name: String, phone: String, imageUri: Uri, userType: String) {
        viewModelScope.launch {
            firestoreUseCases.addUserInfo(userUid, name, phone, userType).collect {
            }
            cloudStorageUseCases.addImageToCloudStorage(imageUri, userUid).collect { response ->
                _imageAddState.value = response
            }
        }
    }
}