package ro.greg.swapestate.domain.use_case.firestore_use_cases

import ro.greg.swapestate.domain.repository.FirestoreRepository




class GetReviews(
    private val repository: FirestoreRepository
) {
    suspend operator fun invoke(
        userId: String
    ) = repository.firestoreGetReviews(userId)
}
