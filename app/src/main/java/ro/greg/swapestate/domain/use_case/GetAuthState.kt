package ro.greg.swapestate.domain.use_case

import ro.greg.swapestate.domain.repository.AuthRepository

class GetAuthState(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.getFirebaseAuthState()
}