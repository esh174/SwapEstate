package ro.greg.swapestate.domain.use_case.auth_use_cases

import ro.greg.swapestate.domain.repository.AuthRepository



class GetUserUid(
    private val repository: AuthRepository
) {
   operator fun invoke() = repository.getUserUid()
}