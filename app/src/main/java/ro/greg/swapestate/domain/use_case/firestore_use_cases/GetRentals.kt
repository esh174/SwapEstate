package ro.greg.swapestate.domain.use_case.firestore_use_cases

import ro.greg.swapestate.domain.repository.FirestoreRepository


class GetRentals(
    private val repository: FirestoreRepository
) {
    suspend operator fun invoke(
    ) = repository.firestoreGetRentals()
}
