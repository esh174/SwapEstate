package ro.greg.swapestate.domain.use_case.auth_use_cases

import ro.greg.swapestate.domain.repository.AuthRepository


class CreateUserWithEmailAndPassword (
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email : String, password  : String) = repository.firebaseCreateUserWithEmailAndPassword(email , password)
}