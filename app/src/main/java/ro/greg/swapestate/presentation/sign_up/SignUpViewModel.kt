package ro.greg.swapestate.presentation.sign_up

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.use_case.UseCases
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject
constructor(
    private val useCases: UseCases
): ViewModel() {
    private val _signUpState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signUpState: State<Response<Boolean>> = _signUpState


    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            useCases.createUserWithEmailAndPassword(email, password).collect { response ->
                _signUpState.value = response
            }
        }

    }
}