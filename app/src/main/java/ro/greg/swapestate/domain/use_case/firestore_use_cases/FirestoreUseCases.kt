package ro.greg.swapestate.domain.use_case.firestore_use_cases

import ro.greg.swapestate.domain.use_case.*

data class FirestoreUseCases(
    val addUserToFireStore: AddUserToFireStore,
    val addUserInfo: AddUserInfo,
    val getUserInfo: GetUserInfo,
    val addRental : AddRental
)