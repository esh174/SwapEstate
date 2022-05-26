package ro.greg.swapestate.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ro.greg.swapestate.domain.model.Rental
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.model.User
import ro.greg.swapestate.domain.use_case.auth_use_cases.AuthUseCases
import ro.greg.swapestate.domain.use_case.cloud_storage_use_cases.CloudStorageUseCases
import ro.greg.swapestate.domain.use_case.firestore_use_cases.FirestoreUseCases
import javax.inject.Inject


@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val firestoreUseCases: FirestoreUseCases,
    private val cloudStorageUseCases: CloudStorageUseCases
): ViewModel() {


    private val _getProfileImageUrlState = mutableStateOf<Response<String>>(Response.Success(""))
    val getProfileImageUrlState: State<Response<String>> = _getProfileImageUrlState


    private val _getRentalImageUrlState = mutableStateOf<Response<String>>(Response.Success(""))
    val getRentalImageUrlState: State<Response<String>> = _getRentalImageUrlState

    private val _userInfoState = mutableStateOf<Response<User?>>(Response.Loading)
    val userInfoState: State<Response<User?>> = _userInfoState

    private val _rentalsState = mutableStateOf<Response<MutableList<Rental>>>(Response.Loading)
    val rentalsState: State<Response<List<Rental>>> = _rentalsState








    init {

        getRentalsQuery()
    }


    fun getProfileImageUrl(userUid:String){
        viewModelScope.launch {
            cloudStorageUseCases.getImageUrl(userUid).collect { response ->
                _getProfileImageUrlState.value = response
            }
        }
    }

    fun getRentalImageUrl(rentalId:String, page : Int){
        val imageName = rentalId + page.toString()
        viewModelScope.launch {
            cloudStorageUseCases.getImageUrl(imageName).collect { response ->
                _getRentalImageUrlState.value = response
            }
        }
    }



    fun getRentalsQuery(){
        viewModelScope.launch {
            firestoreUseCases.getRentals().collect {response ->
                _rentalsState.value = response
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