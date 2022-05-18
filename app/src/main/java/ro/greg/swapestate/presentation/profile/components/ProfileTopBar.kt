package ro.greg.swapestate.presentation.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.shtistorm.presentation.theme.BackgroundColor
import ro.greg.shtistorm.presentation.theme.LightTextColor
import ro.greg.shtistorm.presentation.theme.OnSurfaceColor
import ro.greg.shtistorm.presentation.theme.PrimaryColor
import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.core.Constants.AUTH_SCREEN
import ro.greg.swapestate.presentation.profile.ProfileViewModel
import ro.greg.swapestate.core.Constants.PROFILE_SCREEN
import ro.greg.swapestate.core.Constants.SIGN_OUT

@Composable
fun ProfileTopBar(
    navController: NavController,
    signOut: () -> Unit
) {
    TopAppBar (
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
                        text = Constants.PROFILE_SCREEN,
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


