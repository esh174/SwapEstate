package ro.greg.swapestate.presentation.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
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
import ro.greg.swapestate.core.Constants.AUTH_SCREEN
import ro.greg.swapestate.core.Constants.PROFILE_SCREEN

@Composable
fun ProfileTopBar(
    navController: NavController,
    signOut: () -> Unit
) {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = BackgroundColor,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = "Share Profile",
                        tint = PrimaryColor,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                            }
                    )
                    Text(
                        text = PROFILE_SCREEN,
                        color = OnSurfaceColor,
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,

                    ) {
                    Icon(
                        imageVector = Icons.Filled.Tune,
                        contentDescription = "Profile Settings",
                        tint = PrimaryColor,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                signOut
                                navController.navigate(AUTH_SCREEN)
                            }
                    )
                }
            }
        }
    )
}


