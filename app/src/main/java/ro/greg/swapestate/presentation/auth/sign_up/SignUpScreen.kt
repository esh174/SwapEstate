package ro.greg.swapestate.presentation.auth.sign_up

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.swapestate.presentation.auth.sign_up.components.SignUpScreenContent
import ro.greg.swapestate.presentation.auth.sign_up.components.SignUpTopBar


@Composable
@InternalCoroutinesApi
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            SignUpTopBar(navController = navController)
        }
    ) {
        it.calculateTopPadding()

        SignUpScreenContent(
            signUpWithEmail = {email: String, password: String -> viewModel.signUp(email, password)},
            navController = navController
        )
    }


}
