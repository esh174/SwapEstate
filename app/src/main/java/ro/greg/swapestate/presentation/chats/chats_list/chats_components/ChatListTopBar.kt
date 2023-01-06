package ro.greg.swapestate.presentation.chats.chats_list.chats_components



import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ro.greg.shtistorm.presentation.theme.BackgroundColor
import ro.greg.shtistorm.presentation.theme.OnSurfaceColor
import ro.greg.shtistorm.presentation.theme.PrimaryColor
import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.presentation.chats.chats_list.ChatsListViewModel

@Composable
fun ChatListTopBar(
    navController: NavController,
    viewModel: ChatsListViewModel,
) {

    TopAppBar (
        elevation = 0.dp,
        backgroundColor = BackgroundColor,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),

                ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    Text(
                        text = "Dialogs",
                        color = OnSurfaceColor,
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,

                    ) {
                    Icon(
                        imageVector = Icons.Filled.AddAlert,
                        contentDescription = "Planned Messages",
                        tint = PrimaryColor,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                navController.navigate(Constants.PLANNED_MESSAGES_SCREEN)
                            }
                    )
                }
            }
        }
    )


}


