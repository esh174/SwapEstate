package ro.greg.swapestate.domain.use_case.auth_use_cases

data class AuthUseCases(
    val isUserAuthenticated: IsUserAuthenticated,
    val signInAnonymously: SignInAnonymously,
    val createUserWithEmailAndPassword: CreateUserWithEmailAndPassword,
    val signOut: SignOut,
    val getAuthState: GetAuthState,
    val getUserUid: GetUserUid
)