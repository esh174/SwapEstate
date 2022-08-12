package ro.greg.swapestate.domain.use_case.firestore_use_cases

import ro.greg.swapestate.domain.model.Message
import ro.greg.swapestate.domain.model.Rental
import ro.greg.swapestate.domain.repository.FirestoreRepository

class AddMessage(
    private val repository: FirestoreRepository
) {
    suspend operator fun invoke(
        message: Message,
        chatId: String
    ) = repository.firestoreAddMessage(message, chatId)
}
