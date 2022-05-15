package ro.greg.swapestate.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.use_case.auth_use_cases.AuthUseCases
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
): ViewModel() {
    private val _signOutState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signOutState: State<Response<Boolean>> = _signOutState

    fun signOut() {
        viewModelScope.launch {
            authUseCases.signOut().collect { response ->
                _signOutState.value = response
            }
        }
    }
}