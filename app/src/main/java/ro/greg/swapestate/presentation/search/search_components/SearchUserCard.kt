package ro.greg.swapestate.presentation.search.search_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.model.User
import ro.greg.swapestate.presentation.components.ProgressBar
import ro.greg.swapestate.presentation.profile.ProfileViewModel
import ro.greg.swapestate.presentation.search.SearchScreenViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
fun SearchUserCard(
    navController : NavController,
    user: User,
    viewModel: SearchScreenViewModel
) {


    viewModel.getProfileImageUrl(user.id!!)
    val profileImageUrlState = viewModel.getProfileImageUrlState

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp)){
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(15.dp)
                .size(80.dp),
            elevation = 0.dp,
            onClick = {navController.navigate("${Constants.REVIEWS_SCREEN}/${user.id}")}

            ) {
            when (val response = profileImageUrlState.value) {
                is Response.Loading -> {
                    ProgressBar()
                }
                is Response.Success -> {
                    Image(

                        painter = rememberAsyncImagePainter(model = response.data
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }
            }



        }
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 0.dp,
        ){
            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp)){
                user.name?.let { name ->
                    Text(
                        fontWeight = FontWeight.Bold,
                        text = name,
                        fontSize = 24.sp

                    )
                }
                user.rating.let { rating ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        RatingBar(
                            value = rating.toFloat(),
                            config =  RatingBarConfig()
                                .activeColor(PrimaryColor)
                                .inactiveColor(Color.LightGray)
                                .stepSize(StepSize.HALF)
                                .isIndicator(true)
                                .size(14.dp)
                                .style(RatingBarStyle.HighLighted),
                            onValueChange = {
                            },
                            onRatingChanged = {
                            }
                        )
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = rating.toString(),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(4.dp)
                        )
                    }

                }
            }
        }
    }

}

