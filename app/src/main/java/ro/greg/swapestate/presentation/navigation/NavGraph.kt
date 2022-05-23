package ro.greg.swapestate.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.swapestate.presentation.auth.AuthScreen
import ro.greg.swapestate.presentation.chats.ChatsScreen
import ro.greg.swapestate.presentation.profile.ProfileScreen
import ro.greg.swapestate.presentation.rental_add.RentalAddScreen
import ro.greg.swapestate.presentation.renter_description.RenterDecriptionScreen
import ro.greg.swapestate.presentation.search.SearchScreen
import ro.greg.swapestate.presentation.sign_up.SignUpScreen
import ro.greg.swapestate.presentation.user_details.UserDetailsScreen

@Composable
@InternalCoroutinesApi
@OptIn(ExperimentalAnimationApi::class)
fun NavGraph (
    navController: NavHostController
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.AuthScreen.route,
       // startDestination = Screen.RentalAddScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(
            route = Screen.AuthScreen.route
        ) {
            AuthScreen(
                navController = navController
            )
        }
        composable(
            route = Screen.SignUpScreen.route
        ) {
            SignUpScreen(
                navController = navController
            )
        }
        composable(
            route = Screen.UserDetailsScreen.route
        ) {
            UserDetailsScreen(
                navController = navController
            )
        }
        composable(
            route = Screen.RenterDecriptionScreen.route
        ) {
            RenterDecriptionScreen(
                navController = navController
            )
        }
        composable(
            route = Screen.RentalAddScreen.route
        ) {
            RentalAddScreen(
                navController = navController
            )
        }
        composable(
            route = Screen.ProfileScreen.route
        ) {
            ProfileScreen(
                navController = navController
            )
        }
        composable(
            Screen.SearchScreen.route
        ) {
            SearchScreen(
                navController = navController
            )
        }
        composable(
            Screen.ChatsScreen.route
        ) {
            ChatsScreen(
                navController = navController
            )
        }
    }
}