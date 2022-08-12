package ro.greg.swapestate.presentation.navigation

import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.core.Constants.AUTH_SCREEN
import ro.greg.swapestate.core.Constants.CHATS_SCREEN
import ro.greg.swapestate.core.Constants.CHAT_SCREEN
import ro.greg.swapestate.core.Constants.PLANNED_MESSAGES_SCREEN
import ro.greg.swapestate.core.Constants.PLANNED_MESSAGES_TEMPLATE_SCREEN
import ro.greg.swapestate.core.Constants.PROFILE_SCREEN
import ro.greg.swapestate.core.Constants.RESERVATION_LIST_SCREEN
import ro.greg.swapestate.core.Constants.RESERVATION_SCREEN
import ro.greg.swapestate.core.Constants.REVIEWS_SCREEN
import ro.greg.swapestate.core.Constants.SEARCH_SCREEN

sealed class Screen(val route: String) {
    object AuthScreen: Screen(AUTH_SCREEN)
    object SignUpScreen: Screen(Constants.SIGN_UP_SCREEN)
    object UserDetailsScreen: Screen(Constants.USER_DETAILS_SCREEN)
    object RenterDecriptionScreen: Screen(Constants.RENTER_DESCRIPTION_SCREEN)
    object RentalAddScreen: Screen(Constants.RENTAL_ADD_SCREEN)
    object ProfileScreen: Screen(PROFILE_SCREEN)
    object SearchScreen: Screen(SEARCH_SCREEN)
    object ChatsListScreen: Screen(CHATS_SCREEN)
    object ChatScreen: Screen(CHAT_SCREEN)
    object ReviewsScreen: Screen(REVIEWS_SCREEN)
    object ReservationScreen: Screen(RESERVATION_SCREEN)
    object ReservationListScreen: Screen(RESERVATION_LIST_SCREEN)
    object PlannedMessagesScreen: Screen(PLANNED_MESSAGES_SCREEN)
    object PlannedMessageTemplateScreen: Screen(PLANNED_MESSAGES_TEMPLATE_SCREEN)
}
