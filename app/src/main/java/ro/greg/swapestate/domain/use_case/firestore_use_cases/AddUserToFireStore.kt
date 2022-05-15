package ro.greg.swapestate.domain.use_case.firestore_use_cases

import androidx.compose.runtime.State
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.repository.FirestoreRepository


class AddUserToFireStore(
    private val repository: FirestoreRepository
) {
    suspend operator fun invoke(
        id: String,
        email: String
    ) = repository.firestoreAddUser(id,email)
}