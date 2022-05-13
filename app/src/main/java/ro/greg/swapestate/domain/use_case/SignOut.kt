package ro.greg.swapestate.domain.use_case

import ro.greg.swapestate.domain.repository.AuthRepository

class SignOut(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.signOut()
}