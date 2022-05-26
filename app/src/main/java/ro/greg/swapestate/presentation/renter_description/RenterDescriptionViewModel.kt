package ro.greg.swapestate.presentation.renter_description

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ro.greg.swapestate.domain.model.Rental
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.model.User
import ro.greg.swapestate.domain.use_case.auth_use_cases.AuthUseCases
import ro.greg.swapestate.domain.use_case.cloud_storage_use_cases.CloudStorageUseCases
import ro.greg.swapestate.domain.use_case.firestore_use_cases.FirestoreUseCases
import javax.inject.Inject



@HiltViewModel
class RenterDescriptionViewModel @Inject
constructor(

    private val cloudStorageUseCases: CloudStorageUseCases
): ViewModel() {
    private val _imageAddState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val imageAddState: State<Response<Boolean>> = _imageAddState



    var userUid = ""
    fun addUserInfo(name: String, imageUri: Uri) {
//        val userUidCol = FirebaseFirestore.getInstance().collection("users").whereEqualTo("name", name).addSnapshotListener { snapshot, error ->
//            val user = snapshot?.toObjects(User::class.java)
//            val userUidCall = user?.elementAt(0)?.id
//            userUid = userUidCall!!
//        }

        viewModelScope.launch {
            cloudStorageUseCases.addImageToCloudStorage(imageUri, name).collect { response ->
                _imageAddState.value = response
            }
        }
    }
}