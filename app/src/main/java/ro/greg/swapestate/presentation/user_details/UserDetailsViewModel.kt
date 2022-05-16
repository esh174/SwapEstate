package ro.greg.swapestate.presentation.user_details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.use_case.auth_use_cases.AuthUseCases
import ro.greg.swapestate.domain.use_case.firestore_use_cases.FirestoreUseCases
import javax.inject.Inject


@HiltViewModel
class UserDetailsViewModel @Inject
constructor(
    private val authUseCases: AuthUseCases,
    private val firestoreUseCases: FirestoreUseCases
): ViewModel() {


    private val userUid get() =  authUseCases.getUserUid()

    fun addUserInfo(name: String, phone: String, imageUri: String, userType: String) {
        viewModelScope.launch {



            firestoreUseCases.addUserInfo(userUid,name, phone, imageUri, userType).collect { response ->

            }


        }

    }
}