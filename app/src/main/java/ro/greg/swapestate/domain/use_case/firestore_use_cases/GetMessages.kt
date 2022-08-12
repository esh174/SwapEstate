package ro.greg.swapestate.domain.use_case.firestore_use_cases

import ro.greg.swapestate.domain.model.User
import ro.greg.swapestate.domain.repository.FirestoreRepository



class GetMessages(

    private val repository: FirestoreRepository
) {
    suspend operator fun invoke(
        chatId: String,
    ) = repository.firestoreGetMessages(chatId)
}