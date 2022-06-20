package ro.greg.swapestate.presentation.reservation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.domain.model.Rental
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.model.User
import ro.greg.swapestate.domain.use_case.auth_use_cases.AuthUseCases
import ro.greg.swapestate.domain.use_case.cloud_storage_use_cases.CloudStorageUseCases
import ro.greg.swapestate.domain.use_case.firestore_use_cases.FirestoreUseCases
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject
constructor(
    private val authUseCases: AuthUseCases,
    private val firestoreUseCases: FirestoreUseCases,
    private val cloudStorageUseCases: CloudStorageUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {



    private val _getProfileImageUrlState = mutableStateOf<Response<String>>(Response.Success(""))
    val getProfileImageUrlState: State<Response<String>> = _getProfileImageUrlState

    private val _userInfoState = mutableStateOf<Response<User?>>(Response.Loading)
    val userInfoState: State<Response<User?>> = _userInfoState

    private val _rentalInfoState = mutableStateOf<Response<Rental?>>(Response.Loading)
    val rentalInfoState: State<Response<Rental?>> = _rentalInfoState

    private val userUid get() =  authUseCases.getUserUid()
     val resid =  savedStateHandle.get<String>(Constants.PARAM_RESERVATION_ID)
    init {
        savedStateHandle.get<String>(Constants.PARAM_RESERVATION_ID)?.let{
                rentalId ->
            getRentalInfo(rentalId)
        }
    }

    fun getProfileImageUrl(id: String){
        viewModelScope.launch {
            cloudStorageUseCases.getImageUrl(id).collect { response ->
                _getProfileImageUrlState.value = response
            }
        }
    }


    fun getRentalInfo(id: String){
        viewModelScope.launch {
            firestoreUseCases.getRental(id).collect { response ->
                _rentalInfoState.value = response
            }
        }
    }

    fun getUserInfo(id: String){
        viewModelScope.launch {
            firestoreUseCases.getUserInfo(id).collect { response ->
                _userInfoState.value = response
            }
        }
    }
}