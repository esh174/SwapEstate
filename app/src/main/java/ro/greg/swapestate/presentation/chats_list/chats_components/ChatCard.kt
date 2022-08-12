package ro.greg.swapestate.presentation.chats_list.chats_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.shtistorm.presentation.theme.PrimaryColor
import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.domain.model.Chat
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.model.User
import ro.greg.swapestate.presentation.chats_list.ChatsListViewModel
import ro.greg.swapestate.presentation.components.ProgressBar
import ro.greg.swapestate.presentation.profile.ProfileViewModel



@OptIn(ExperimentalMaterialApi::class)
@Composable
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
fun ChatCard(
    chat: Chat,
    viewModel: ChatsListViewModel = hiltViewModel(),
    navController: NavController
) {

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp)) {

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 0.dp,
            onClick = { navController.navigate("${Constants.CHAT_SCREEN}/${chat.id}") }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {


                        Card(

                            modifier = Modifier
                                .size(70.dp)
                                ,
                            shape = CircleShape,
                            elevation = 0.dp,

                            ) {

                                    Image(

                                        painter = rememberAsyncImagePainter(
                                            model = viewModel.getUserId(chat)
                                                ?.let { viewModel.cloudStorageGetImageUrl(it) }
                                        ),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        contentScale = ContentScale.Crop

                                    )
                                }






                when (val response =
                    viewModel.getUserId(chat)
                        ?.let { viewModel.getChatCard(it, chat.rentalId).value }) {
                    is Response.Loading -> ProgressBar()
                    is Response.Success -> {
                        val cardmap = response.data
                        Column(modifier = Modifier.padding(horizontal = 10.dp)) {

                            cardmap.getValue("username")?.let {
                                Text(
                                    fontWeight = FontWeight.Bold,
                                    text = it,
                                    fontSize = 18.sp

                                )
                            }
                            cardmap.getValue("rentalName")?.let {
                                Text(
                                    fontWeight = FontWeight.Light,
                                    text = it,
                                    fontSize = 14.sp

                                )
                            }
                        }
                    }
                }
            }
        }

    }


}
