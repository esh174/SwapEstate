package ro.greg.swapestate.domain.use_case.firestore_use_cases

import ro.greg.swapestate.domain.repository.FirestoreRepository


class GetUserInfo(
    private val repository: FirestoreRepository
) {
     operator fun invoke(
        id:String
    ) = repository.firestoreGetUserInfo(id)
}