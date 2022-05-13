package ro.greg.swapestate.presentation.sign_up

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.swapestate.presentation.sign_up.components.SignUpContent
import ro.greg.swapestate.presentation.sign_up.components.SignUpTopBar

@OptIn(InternalCoroutinesApi::class)
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavController
) {
    Scaffold(
        topBar = {
            SignUpTopBar(navController)
        }
    ) {
        it.calculateTopPadding()
        SignUpContent(navController)

    }
}