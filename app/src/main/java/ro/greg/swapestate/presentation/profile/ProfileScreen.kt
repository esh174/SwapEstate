package ro.greg.swapestate.presentation.profile

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.swapestate.core.Utils.Companion.printError
import ro.greg.swapestate.domain.model.Response.*
import ro.greg.swapestate.presentation.components.BottomNavigationBar
import ro.greg.swapestate.presentation.components.ProgressBar
import ro.greg.swapestate.presentation.navigation.Screen.AuthScreen
import ro.greg.swapestate.presentation.profile.components.ProfileContent
import ro.greg.swapestate.presentation.profile.components.ProfileTopBar

@Composable
@InternalCoroutinesApi
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            ProfileTopBar(navController = navController,
                signOut = { viewModel.signOut() })
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        it.calculateTopPadding()
        ProfileContent(navController=  navController,viewModel = viewModel)
    }
    when(val response = viewModel.signOutState.value) {
        is Loading -> ProgressBar()
        is Success -> if (response.data) {
            LaunchedEffect(response.data) {
                navController.popBackStack()
                navController.navigate(AuthScreen.route)
            }
        }
        is Error -> LaunchedEffect(Unit) {
            printError(response.message)
        }
    }
}