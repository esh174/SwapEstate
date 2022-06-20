package ro.greg.swapestate.presentation.reservation_list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.shtistorm.presentation.theme.*
import ro.greg.swapestate.R
import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.domain.model.Rental
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.model.User
import ro.greg.swapestate.presentation.components.ProgressBar
import ro.greg.swapestate.presentation.reservation.ReservationViewModel
import ro.greg.swapestate.presentation.reservation_list.components.ReservationListTopBar
import ro.greg.swapestate.presentation.reviews.ReviewsViewModel
import ro.greg.swapestate.presentation.reviews.components.ReviewCard
import ro.greg.swapestate.presentation.search.SearchScreenViewModel
import ro.greg.swapestate.presentation.search.search_components.SearchUserCard

@OptIn(InternalCoroutinesApi::class, ExperimentalMaterialApi::class)
@Composable
fun ReservationListScreen(
    viewModel: ReservationViewModel = hiltViewModel(),
    navController: NavController
) {

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            ReservationListTopBar(navController = navController)
        }
    ) {

        it.calculateTopPadding()
        Divider(color = Color.LightGray, thickness = 1.dp)

        Box(modifier = Modifier.fillMaxSize()
            .background(color = LightPrimaryColor)) {
            Column() {
                Spacer(Modifier.height(14.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = 4.dp,
                shape = RoundedCornerShape(12.dp)
            ) {

                Column(modifier = Modifier
                    .fillMaxWidth(),) {


                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp).padding(top = 10.dp)
                    ) {
                        Card(
                            shape = CircleShape,
                            modifier = Modifier
                                .padding(15.dp)
                                .size(100.dp),
                            elevation = 0.dp,
                            onClick = {}

                        ) {

                            Image(

                                painterResource(R.drawable.movie1),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentScale = ContentScale.Crop
                            )


                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            elevation = 0.dp,
                        ) {
                            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp)) {
                                Text(
                                    fontWeight = FontWeight.Bold,
                                    text = "Test Test",
                                    fontSize = 24.sp

                                )
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RatingBar(
                                        value = 4.toFloat(),
                                        config = RatingBarConfig()
                                            .activeColor(PrimaryColor)
                                            .inactiveColor(Color.LightGray)
                                            .stepSize(StepSize.HALF)
                                            .isIndicator(true)
                                            .size(18.dp)
                                            .style(RatingBarStyle.HighLighted),
                                        onValueChange = {
                                        },
                                        onRatingChanged = {
                                        }
                                    )
                                    Text(
                                        fontWeight = FontWeight.Bold,
                                        text = 4.toString(),
                                        fontSize = 14.sp,
                                        modifier = Modifier.padding(4.dp)
                                    )
                                }


                            }
                        }
                    }
                    Divider(color = Color.LightGray, thickness = 1.dp)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth().padding(vertical = 10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .width(((LocalConfiguration.current.screenWidthDp) / 1.3).dp),
                            horizontalArrangement = Arrangement.Start
                        ) {

                            Text(
                                text = "Yekaterinburg, Malysheva st.1, 12, ap. 2 "
                            )

                        }
                        Row(
                            modifier = Modifier
                                .width(((LocalConfiguration.current.screenWidthDp) / 3.7).dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Icon(
                                imageVector = Icons.Filled.SubdirectoryArrowRight,
                                contentDescription = "",
                                tint = PrimaryColor,
                                modifier = Modifier
                                    .size(60.dp)
                                    .padding(16.dp)
                                    .clickable {

                                    }
                            )
                        }





                    }
                    Divider(color = Color.LightGray, thickness = 1.dp)




                    Column( modifier = Modifier
                        .fillMaxWidth().padding(15.dp)){
                        Text(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .padding(vertical = 15.dp),
                            text = "30.06.2022 - 12.07.2022"
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = null,
                                tint = PrimaryColor,
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .size(30.dp),
                            )

                            Text(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                text = "1 person"

                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Pets,
                                contentDescription = null,
                                tint = PrimaryColor,
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .size(30.dp),
                            )

                            Text(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                text = "No pets"

                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp).padding(bottom = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ChildCare,
                                contentDescription = null,
                                tint = PrimaryColor,
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .size(30.dp),
                            )

                            Text(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                text = "No kids"

                            )
                        }
                        Text(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .padding(bottom = 15.dp),
                        text = "Coming for my finals. We discussed it maybe two weeks or so. I'll live alone, won't have time for parties - finals :("
                    )
                    }}


                }
            }
        }

    }
}



