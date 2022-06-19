package ro.greg.swapestate.presentation.reviews.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ro.greg.shtistorm.presentation.theme.BackgroundColor
import ro.greg.shtistorm.presentation.theme.OnSurfaceColor
import ro.greg.shtistorm.presentation.theme.PrimaryColor
import ro.greg.shtistorm.presentation.theme.SurfaceColor
import ro.greg.swapestate.core.Constants



@Composable
fun ReviewsTopBar(
    navController: NavController,
){
    TopAppBar (
        elevation = 0.dp,
        backgroundColor = SurfaceColor,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),

                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            navController.navigateUp()
                        }
                )
                Text(
                    text = "Received reviews",
                )
            }
        }
    )
}
