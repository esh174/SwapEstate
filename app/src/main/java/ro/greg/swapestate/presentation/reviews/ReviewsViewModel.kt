package ro.greg.swapestate.presentation.reviews

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.model.Review
import ro.greg.swapestate.domain.model.User
import ro.greg.swapestate.domain.use_case.auth_use_cases.AuthUseCases
import ro.greg.swapestate.domain.use_case.cloud_storage_use_cases.CloudStorageUseCases
import ro.greg.swapestate.domain.use_case.firestore_use_cases.FirestoreUseCases
import javax.inject.Inject


@HiltViewModel
class ReviewsViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val firestoreUseCases: FirestoreUseCases,
    private val cloudStorageUseCases: CloudStorageUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _userInfoState = mutableStateOf<Response<User?>>(Response.Loading)
    val userInfoState: State<Response<User?>> = _userInfoState

    val imagesRef = FirebaseStorage.getInstance().getReference("images")
    val usersRef = FirebaseFirestore.getInstance().collection("users")

    private val _getReviewsState = mutableStateOf<Response<List<Review>>>(Response.Loading)
    val getReviewsState: State<Response<List<Review>>> = _getReviewsState

    val _reviewsByStars = mutableMapOf<Int, Int>()
    val reviewsByStars: Map<Int, Int> = _reviewsByStars

    val userUid get() = authUseCases.getUserUid()

    init {
        savedStateHandle.get<String>(Constants.PARAM_USER_ID)?.let { currentUserId ->
            getReviews(currentUserId)
            getUserInfo(currentUserId)
        }
    }

    val sumReviews: Int = reviewsByStars[5]!! +
            reviewsByStars[4]!! +
            reviewsByStars[3]!! +
            reviewsByStars[2]!! +
            reviewsByStars[1]!!
    val prodReviews = reviewsByStars[5]!! * 5 +
            reviewsByStars[4]!! * 4 +
            reviewsByStars[3]!! * 3 +
            reviewsByStars[2]!! * 2 +
            reviewsByStars[1]!! * 1

    val avgRating = prodReviews / sumReviews

    private fun getReviews(id: String) {
        viewModelScope.launch {
            firestoreUseCases.getReviews(id).collect { response ->
                _getReviewsState.value = response
            }
        }
    }

    fun getUserInfo(id: String) {
        runBlocking {
            val fiveStars = usersRef.document(id)
                .collection("reviews").whereEqualTo("rating", 5)
                .get().await().documents.size
            val fourStars = usersRef.document(id)
                .collection("reviews").whereEqualTo("rating", 4)
                .get().await().documents.size
            val threeStars = usersRef.document(id)
                .collection("reviews").whereEqualTo("rating", 3)
                .get().await().documents.size
            val twoStars = usersRef.document(id)
                .collection("reviews").whereEqualTo("rating", 2)
                .get().await().documents.size
            val oneStar = usersRef.document(id)
                .collection("reviews").whereEqualTo("rating", 1)
                .get().await().documents.size

            _reviewsByStars.put(1, oneStar)
            _reviewsByStars.put(2, twoStars)
            _reviewsByStars.put(3, threeStars)
            _reviewsByStars.put(4, fourStars)
            _reviewsByStars.put(5, fiveStars)
        }
    }

    fun cloudStorageGetImageUrl(fileName: String?): String {
        var url: String = ""
        runBlocking {
            url = imagesRef.child(fileName!!).downloadUrl.await().toString()
        }
        return url
    }
}


