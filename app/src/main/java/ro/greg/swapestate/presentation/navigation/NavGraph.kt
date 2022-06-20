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
import ro.greg.swapestate.presentation.chat.ChatScreen
import ro.greg.swapestate.presentation.chats_list.ChatsListScreen
import ro.greg.swapestate.presentation.planned_message_template.PlannedMessageTemplateScreen
import ro.greg.swapestate.presentation.planned_messages.PlannedMesagesScreen
import ro.greg.swapestate.presentation.profile.ProfileScreen
import ro.greg.swapestate.presentation.rental_add.RentalAddScreen
import ro.greg.swapestate.presentation.renter_description.RenterDecriptionScreen
import ro.greg.swapestate.presentation.reservation.ReservationScreen
import ro.greg.swapestate.presentation.reservation_list.ReservationListScreen
import ro.greg.swapestate.presentation.reviews.ReviewsScreen
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
       // startDestination = Screen.RenterDecriptionScreen.route,
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
            Screen.ReviewsScreen.route + "/{userId}"
        ) {
            ReviewsScreen(
                navController = navController
            )
        }
        composable(
                Screen.ChatsListScreen.route
                ) {
            ChatsListScreen(
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
            Screen.ChatsListScreen.route
        ) {
            ChatsListScreen(
                navController = navController
            )
        }
        composable(
            Screen.PlannedMessagesScreen.route
        ) {
            PlannedMesagesScreen(
                navController = navController
            )
        }
        composable(
            Screen.PlannedMessageTemplateScreen.route
        ) {
            PlannedMessageTemplateScreen(
                navController = navController
            )
        }
        composable(
            Screen.ChatScreen.route + "/{chatId}"
        ) {
            ChatScreen(
                navController = navController
            )
        }
        composable(
            Screen.ReservationScreen.route + "/{reservationId}"
        ) {
            ReservationScreen(
                navController = navController
            )
        }
        composable(
            Screen.ReservationListScreen.route
        ) {
            ReservationListScreen(
                navController = navController
            )
        }
    }
}