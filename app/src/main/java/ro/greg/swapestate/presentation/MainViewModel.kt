package ro.greg.swapestate.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import ro.greg.swapestate.domain.use_case.UseCases
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    val isUserAuthenticated get() = useCases.isUserAuthenticated()

    fun getAuthState() = liveData(Dispatchers.IO) {
        useCases.getAuthState().collect { response ->
            emit(response)
        }
    }
}