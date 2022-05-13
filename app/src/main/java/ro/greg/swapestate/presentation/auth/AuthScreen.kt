package ro.greg.swapestate.presentation.auth

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.core.Utils.Companion.printError
import ro.greg.swapestate.domain.model.Response.*
import ro.greg.swapestate.presentation.auth.components.AuthContent
import ro.greg.swapestate.presentation.auth.components.AuthTopBar
import ro.greg.swapestate.presentation.components.ProgressBar
import ro.greg.swapestate.presentation.navigation.Screen.ProfileScreen

@Composable
@InternalCoroutinesApi
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navController: NavController
) {
    Scaffold(
        topBar = {
            AuthTopBar()
        }
    ) {
        it.calculateTopPadding()
        AuthContent({ navController.navigate(Constants.SIGN_UP_SCREEN) })
    }

    when(val response = viewModel.signInState.value) {
        is Loading -> ProgressBar()
        is Success -> if (response.data) {
            LaunchedEffect(response.data) {
                navController.navigate(ProfileScreen.route)
            }
        }
        is Error -> LaunchedEffect(Unit) {
            printError(response.message)
        }
    }
}