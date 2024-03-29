package ro.greg.swapestate.presentation.user_details

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.swapestate.core.Constants.RENTAL_ADD_SCREEN
import ro.greg.swapestate.core.Constants.RENTER_DESCRIPTION_SCREEN
import ro.greg.swapestate.presentation.auth.sign_up.SignUpViewModel
import ro.greg.swapestate.presentation.auth.sign_up.components.SignUpScreenContent
import ro.greg.swapestate.presentation.auth.sign_up.components.SignUpTopBar
import ro.greg.swapestate.presentation.user_details.components.UserDetailsContent


@Composable
@InternalCoroutinesApi
fun UserDetailsScreen(
    navController: NavController,
    viewModel: UserDetailsViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            SignUpTopBar(navController = navController)
        }
    ) {
        it.calculateTopPadding()
        UserDetailsContent(
            proceedOwner = { navController.navigate(RENTAL_ADD_SCREEN) },
             proceedRenter = { navController.navigate(RENTER_DESCRIPTION_SCREEN) }
        )
    }
}
