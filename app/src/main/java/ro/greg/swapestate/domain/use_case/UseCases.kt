package ro.greg.swapestate.domain.use_case

data class UseCases(
    val isUserAuthenticated: IsUserAuthenticated,
    val signInAnonymously: SignInAnonymously,
     val createUserWithEmailAndPassword: CreateUserWithEmailAndPassword,
    val signOut: SignOut,
    val getAuthState: GetAuthState
)