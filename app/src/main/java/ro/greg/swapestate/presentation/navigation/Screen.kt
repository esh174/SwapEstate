package ro.greg.swapestate.presentation.navigation

import ro.greg.swapestate.core.Constants.AUTH_SCREEN
import ro.greg.swapestate.core.Constants.HOPE_SCREEN
import ro.greg.swapestate.core.Constants.PROFILE_SCREEN
import ro.greg.swapestate.core.Constants.SIGN_UP_SCREEN

sealed class Screen(val route: String) {
    object AuthScreen: Screen(AUTH_SCREEN)
    object SignUpScreen: Screen(SIGN_UP_SCREEN)
    object HopeScreen: Screen(HOPE_SCREEN)
    object ProfileScreen: Screen(PROFILE_SCREEN)
}