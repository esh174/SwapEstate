package ro.greg.swapestate.presentation.sign_up.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ro.greg.swapestate.core.Constants
import ro.greg.shtistorm.presentation.theme.SurfaceColor


@Composable
fun SignUpTopBar(navController: NavController) {
    TopAppBar (
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
                text = Constants.SIGN_UP_SCREEN,
            )
        }
    }
)
}