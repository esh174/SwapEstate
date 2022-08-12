package ro.greg.swapestate.presentation.chats_list.chats_components



import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import ro.greg.shtistorm.presentation.theme.BackgroundColor
import ro.greg.shtistorm.presentation.theme.OnSurfaceColor
import ro.greg.shtistorm.presentation.theme.PrimaryColor
import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.domain.model.Chat
import ro.greg.swapestate.domain.model.Rental
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.presentation.chat.ChatViewModel
import ro.greg.swapestate.presentation.chats_list.ChatsListViewModel
import ro.greg.swapestate.presentation.components.ProgressBar

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


