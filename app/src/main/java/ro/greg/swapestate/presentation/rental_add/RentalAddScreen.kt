package ro.greg.swapestate.presentation.rental_add

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.swapestate.presentation.rental_add.components.RentalAddContent
import ro.greg.swapestate.presentation.auth.sign_up.components.SignUpTopBar
import ro.greg.swapestate.presentation.user_details.UserDetailsViewModel
import ro.greg.swapestate.presentation.user_details.components.UserDetailsContent


@Composable
@InternalCoroutinesApi
fun RentalAddScreen(
    navController: NavController,
) {
    Scaffold(

    ) {
        it.calculateTopPadding()
        RentalAddContent()
    }


}


