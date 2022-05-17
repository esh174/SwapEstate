package ro.greg.swapestate.domain.use_case.firestore_use_cases

import android.net.Uri
import ro.greg.swapestate.domain.repository.FirestoreRepository


class AddUserInfo(
    private val repository: FirestoreRepository
) {
    suspend operator fun invoke(
        id:String,
        name: String,
        phone : String,
        userType: String
    ) = repository.firestoreAddInfo(id, name, phone, userType)
}