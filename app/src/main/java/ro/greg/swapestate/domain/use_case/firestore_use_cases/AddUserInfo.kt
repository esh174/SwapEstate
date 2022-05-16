package ro.greg.swapestate.domain.use_case.firestore_use_cases

import ro.greg.swapestate.domain.repository.FirestoreRepository


class AddUserInfo(
    private val repository: FirestoreRepository
) {
    suspend operator fun invoke(
        id:String,
        name: String,
        phone : String,
        imageUri: String,
        userType: String
    ) = repository.firestoreAddInfo(id, name, phone, imageUri, userType)
}