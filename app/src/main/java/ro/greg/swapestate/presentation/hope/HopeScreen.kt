package ro.greg.swapestate.presentation.hope

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.swapestate.presentation.hope.components.HopeScreenContent
import ro.greg.swapestate.presentation.hope.components.HopeTopBar



@Composable
@InternalCoroutinesApi
fun HopeScreen(
    navController: NavController,
    viewModel: HopeViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            HopeTopBar(navController = navController)
        }
    ) {
        it.calculateTopPadding()

        HopeScreenContent(
            signUpWithEmail = {email: String, password: String -> viewModel.signUp(email, password)}
        )
    }


}
