package ro.greg.swapestate.presentation.chats.chat_item.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import ro.greg.shtistorm.presentation.theme.BackgroundColor
import ro.greg.shtistorm.presentation.theme.PrimaryColor
import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.domain.model.Chat
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.presentation.chats.chat_item.ChatViewModel
import ro.greg.swapestate.presentation.components.ProgressBar

@Composable
fun ChatTopBar(
    navController: NavController,
    viewModel: ChatViewModel,
    chat: Chat
) {
    viewModel.getProfileImageUrl(viewModel.getUserId(chat).toString())
    viewModel.getUserInfo(viewModel.getUserId(chat).toString())
    TopAppBar (
        elevation = 0.dp,
        backgroundColor = BackgroundColor,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),

                ) {
                Row(

                    horizontalArrangement = Arrangement.Start
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
                    Card(
                        shape = CircleShape,
                        modifier = Modifier
                            .size(50.dp),
                        elevation = 0.dp,

                        ) {
                        when (val response = viewModel.getProfileImageUrlState.value) {
                            is Response.Loading -> {
                                ProgressBar()
                            }
                            is Response.Success -> {
                                Image(

                                    painter = rememberAsyncImagePainter(
                                        model = response.data
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    contentScale = ContentScale.Crop

                                )
                            }
                        }


                    }

                }
                Row(modifier = Modifier.padding(horizontal = 10.dp)){
                    when (val response = viewModel.userInfoState.value) {
                        is Response.Loading -> {
                            ProgressBar()
                        }
                        is Response.Success -> {
                            Column() {
                                response.data!!.name?.let {
                                    Text(
                                        text = it,
                                        fontSize = 18.sp
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .clickable {
                                            navController.navigate("${Constants.REVIEWS_SCREEN}/${viewModel.getUserId(chat)}")
                                                   },
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    RatingBar(
                                        value = response.data!!.rating.toFloat(),
                                        config =  RatingBarConfig()
                                            .activeColor(PrimaryColor)
                                            .inactiveColor(Color.LightGray)
                                            .stepSize(StepSize.HALF)
                                            .isIndicator(true)
                                            .size(13.dp)
                                            .style(RatingBarStyle.HighLighted),
                                        onValueChange = {
                                        },
                                        onRatingChanged = {
                                        }
                                    )
                                    Text(
                                        fontWeight = FontWeight.Bold,
                                        text = response.data!!.rating.toString(),
                                        fontSize = 18.sp,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }

                        }
                    }
                }

            }
        }

    )


}


