package ro.greg.swapestate.presentation.reviews.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.OtherHouses
import androidx.compose.material.icons.filled.Reviews
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.shtistorm.presentation.theme.LightPrimaryColor
import ro.greg.shtistorm.presentation.theme.PrimaryColor
import ro.greg.shtistorm.presentation.theme.SurfaceColor
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.presentation.components.ProgressBar
import ro.greg.swapestate.presentation.profile.components.ProfileCard

import ro.greg.swapestate.presentation.reviews.ReviewsViewModel


@OptIn(InternalCoroutinesApi::class)
@Composable
fun ReviewsContent(
    navController: NavController,
    viewModel: ReviewsViewModel
) {

    Box(modifier = Modifier.fillMaxSize()
        .background(color = LightPrimaryColor)){

        Column(
            modifier = Modifier
                .fillMaxWidth()
            ,
        ) {
            Divider(color = Color.LightGray, thickness = 1.dp)
            Card(modifier = Modifier
                .fillMaxWidth(),
                elevation = 4.dp,
                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .background(color = SurfaceColor),
                ) {


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RatingBar(
                            value = viewModel.avgRating.toFloat(),
                            config = RatingBarConfig()
                                .activeColor(PrimaryColor)
                                .inactiveColor(Color.LightGray)
                                .stepSize(StepSize.HALF)
                                .isIndicator(true)
                                .size(20.dp)
                                .style(RatingBarStyle.HighLighted),
                            onValueChange = {
                            },
                            onRatingChanged = {
                            }
                        )
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = viewModel.avgRating.toString()+"/5.0",
                            fontSize = 25.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                    Row(
                        modifier = Modifier
                        .fillMaxWidth()
                    ){
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier
                                    .width(((LocalConfiguration.current.screenWidthDp) / 1.3).dp),
                            ){
                            Text(
                                fontSize = 22.sp,
                                text = "Excellent")
                            }
                            Row(
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier
                                    .width(((LocalConfiguration.current.screenWidthDp) / 3.7).dp)
                            ){
                                Text(fontWeight = FontWeight.Bold,
                                    fontSize = 22.sp,
                                    text = viewModel.reviewsByStars[5].toString())
                            }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                .width(((LocalConfiguration.current.screenWidthDp) / 1.3).dp),
                        ){
                            Text(
                                fontSize = 22.sp,
                                text = "Very Good")
                        }
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier
                                .width(((LocalConfiguration.current.screenWidthDp) / 3.7).dp)
                        ){
                            Text(fontWeight = FontWeight.Bold,
                                fontSize = 22.sp,
                                text = viewModel.reviewsByStars[4].toString())
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                .width(((LocalConfiguration.current.screenWidthDp) / 1.3).dp),
                        ){
                            Text(
                                fontSize = 22.sp,
                                text = "Good")
                        }
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier
                                .width(((LocalConfiguration.current.screenWidthDp) / 3.7).dp)
                        ){
                            Text(fontWeight = FontWeight.Bold,
                                fontSize = 22.sp,
                                text = viewModel.reviewsByStars[3].toString())
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                .width(((LocalConfiguration.current.screenWidthDp) / 1.3).dp),
                        ){
                            Text(
                                fontSize = 22.sp,
                                text = "Fair")
                        }
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier
                                .width(((LocalConfiguration.current.screenWidthDp) / 3.7).dp)
                        ){
                            Text(fontWeight = FontWeight.Bold,
                                fontSize = 22.sp,
                                text = viewModel.reviewsByStars[2].toString())
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom =  10.dp)
                    ){
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                .width(((LocalConfiguration.current.screenWidthDp) / 1.3).dp),
                        ){
                            Text(
                                fontSize = 22.sp,
                                text = "Poor")
                        }
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier
                                .width(((LocalConfiguration.current.screenWidthDp) / 3.7).dp)
                        ){
                            Text(fontWeight = FontWeight.Bold,
                                fontSize = 22.sp,
                                text = viewModel.reviewsByStars[1].toString())
                        }
                    }


                }
            }

            when(val reviewsResponse = viewModel.getReviewsState.value) {
                is Response.Loading -> ProgressBar()
                is Response.Success -> Box(
                ) {
                    LazyColumn {
                        items(
                            items = reviewsResponse.data
                        ) { review ->

                            ReviewCard(review = review, viewModel = viewModel)
                        }
                    }
                }
            }


        }
    }
}




