package ro.greg.swapestate.presentation.renter_description

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.swapestate.presentation.sign_up.components.SignUpTopBar
import ro.greg.swapestate.presentation.user_details.UserDetailsViewModel
import ro.greg.swapestate.presentation.user_details.components.UserDetailsContent


@Composable
@InternalCoroutinesApi
fun RenterDecriptionScreen(
    navController: NavController,
) {
    Scaffold(

    ) {
        it.calculateTopPadding()
        RenterDescriptionContent()


    }


}