package ro.greg.swapestate.presentation.navigation

import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.core.Constants.AUTH_SCREEN
import ro.greg.swapestate.core.Constants.CHATS_SCREEN
import ro.greg.swapestate.core.Constants.PROFILE_SCREEN
import ro.greg.swapestate.core.Constants.SEARCH_SCREEN

sealed class Screen(val route: String) {
    object AuthScreen: Screen(AUTH_SCREEN)
    object SignUpScreen: Screen(Constants.SIGN_UP_SCREEN)
    object UserDetailsScreen: Screen(Constants.USER_DETAILS_SCREEN)
    object RenterDecriptionScreen: Screen(Constants.RENTER_DESCRIPTION_SCREEN)
    object RentalAddScreen: Screen(Constants.RENTAL_ADD_SCREEN)
    object ProfileScreen: Screen(PROFILE_SCREEN)
    object SearchScreen: Screen(SEARCH_SCREEN)
    object ChatsScreen: Screen(CHATS_SCREEN)

}