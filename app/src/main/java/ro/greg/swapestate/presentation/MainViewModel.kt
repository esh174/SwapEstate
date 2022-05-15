package ro.greg.swapestate.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import ro.greg.swapestate.domain.use_case.auth_use_cases.AuthUseCases
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
): ViewModel() {
    val isUserAuthenticated get() = authUseCases.isUserAuthenticated()

    fun getAuthState() = liveData(Dispatchers.IO) {
        authUseCases.getAuthState().collect { response ->
            emit(response)
        }
    }
}