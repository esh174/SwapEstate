package ro.greg.swapestate.presentation.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.model.Response.Success
import ro.greg.swapestate.domain.use_case.auth_use_cases.AuthUseCases
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
): ViewModel() {
    private val _signInState = mutableStateOf<Response<Boolean>>(Success(false))
    val signInState: State<Response<Boolean>> = _signInState

    fun signIn() {
        viewModelScope.launch {
            authUseCases.signInAnonymously().collect { response ->
                _signInState.value = response
            }
        }
    }
}