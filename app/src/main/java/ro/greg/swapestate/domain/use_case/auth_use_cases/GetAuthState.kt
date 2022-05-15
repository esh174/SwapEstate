package ro.greg.swapestate.domain.use_case.auth_use_cases

import ro.greg.swapestate.domain.repository.AuthRepository

class GetAuthState(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.getFirebaseAuthState()
}