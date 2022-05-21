package ro.greg.swapestate.domain.use_case.firestore_use_cases

import ro.greg.swapestate.domain.model.Rental
import ro.greg.swapestate.domain.repository.FirestoreRepository

class AddRental(
    private val repository: FirestoreRepository
) {
    suspend operator fun invoke(
        rental: Rental
    ) = repository.firestoreAddRental(rental)
}