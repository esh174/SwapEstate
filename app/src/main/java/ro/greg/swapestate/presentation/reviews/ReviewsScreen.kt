package ro.greg.swapestate.presentation.reviews

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.swapestate.core.Utils
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.presentation.components.BottomNavigationBar
import ro.greg.swapestate.presentation.components.ProgressBar
import ro.greg.swapestate.presentation.navigation.Screen
import ro.greg.swapestate.presentation.profile.ProfileViewModel
import ro.greg.swapestate.presentation.profile.components.ProfileContent
import ro.greg.swapestate.presentation.profile.components.ProfileTopBar
import ro.greg.swapestate.presentation.reviews.components.ReviewsContent
import ro.greg.swapestate.presentation.reviews.components.ReviewsTopBar


@Composable
@InternalCoroutinesApi
fun ReviewsScreen(
    navController: NavController,
    viewModel: ReviewsViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            ReviewsTopBar(navController = navController)
        },

    ) {
        it.calculateTopPadding()
        ReviewsContent(navController = navController, viewModel = viewModel)

    }




}