package ro.greg.swapestate.presentation.reservation

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.swapestate.presentation.reservation.components.ReservationContent
import ro.greg.swapestate.presentation.reservation.components.ReservationTopBar


@OptIn(InternalCoroutinesApi::class)
@Composable
fun ReservationScreen(
    viewModel: ReservationViewModel = hiltViewModel(),
    navController: NavController
) {

        Scaffold(
            modifier = Modifier.systemBarsPadding(),
            topBar = {
                ReservationTopBar(navController = navController)
            }
        ) {

            it.calculateTopPadding()
            Divider(color = Color.LightGray, thickness = 1.dp)
            ReservationContent(viewModel = viewModel,navController = navController )
        }
    }





