package ro.greg.swapestate.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.use_case.auth_use_cases.AuthUseCases
import ro.greg.swapestate.domain.use_case.cloud_storage_use_cases.CloudStorageUseCases
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val cloudStorageUseCases: CloudStorageUseCases
): ViewModel() {
    private val _signOutState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signOutState: State<Response<Boolean>> = _signOutState

    private val _getProfileImageUrlState = mutableStateOf<Response<String>>(Response.Success(""))
    val getProfileImageUrlState: State<Response<String>> = _getProfileImageUrlState

    init {
        getProfileImageUrl()
    }

    private val userUid get() = authUseCases.getUserUid()

    fun getProfileImageUrl(){
        viewModelScope.launch {
            cloudStorageUseCases.getImageUrl(userUid).collect { response ->
               _getProfileImageUrlState.value = response
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authUseCases.signOut().collect { response ->
                _signOutState.value = response
            }
        }
    }
}