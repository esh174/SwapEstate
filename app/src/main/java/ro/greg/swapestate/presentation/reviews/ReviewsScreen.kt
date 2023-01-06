package ro.greg.swapestate.presentation.reviews

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
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