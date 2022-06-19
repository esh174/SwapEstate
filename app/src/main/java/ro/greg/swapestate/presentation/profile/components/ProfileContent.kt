package ro.greg.swapestate.presentation.profile.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.OtherHouses
import androidx.compose.material.icons.filled.Reviews
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.shtistorm.presentation.theme.*
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.R
import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.core.Constants.WELCOME_MESSAGE
import ro.greg.swapestate.domain.model.User
import ro.greg.swapestate.presentation.components.ProgressBar
import ro.greg.swapestate.presentation.profile.ProfileViewModel

@OptIn(InternalCoroutinesApi::class)
@Composable
fun ProfileContent(
    navController: NavController,
    viewModel: ProfileViewModel
) {
    val userInfoState = viewModel.userInfoState
    val userId = viewModel.userUid
    Column(modifier = Modifier.fillMaxSize()){
        Divider(color = Color.LightGray, thickness = 1.dp)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = LightPrimaryColor)
                .verticalScroll(rememberScrollState())
            ,
        ) {

            Card(modifier = Modifier
                .fillMaxWidth(),
                elevation = 4.dp,
                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = SurfaceColor),
                ) {
                    when (val response = userInfoState.value) {
                        is Response.Loading -> {
                            ProgressBar()
                        }
                        is Response.Success -> {
                            response.data?.let {
                                ProfileCard(
                                    user = it
                                )
                            }
                        }
                    }
                }

            }

            Spacer(Modifier.height(14.dp))

            Card(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
                elevation = 10.dp,
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = SurfaceColor),
                ) {

                    Text(
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp),
                        text = "Reservations",
                        fontSize = 20.sp
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                    ){
                        Icon(
                            imageVector = Icons.Filled.OtherHouses,
                            contentDescription = null,
                            tint = LightTextColor,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .size(24.dp),
                        )
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = "1 reservation",
                            fontSize = 20.sp
                        )
                    }
                    Divider(color = Color.LightGray, thickness = 1.dp)
                    TextButton(

                        onClick = {
                            /*TODO*/
                        },
                    ) {
                        Text(modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Go to reservations",
                            color =  PrimaryColor,
                            fontSize = 18.sp
                        )
                    }
                }

            }
            Spacer(Modifier.height(14.dp))
            Card(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
                elevation = 10.dp,
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = SurfaceColor),
                ) {

                    Text(
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp),
                        text = "My reviews",
                        fontSize = 20.sp
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                    ){
                        Icon(
                            imageVector = Icons.Filled.Reviews,
                            contentDescription = null,
                            tint = LightTextColor,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .size(24.dp),
                        )
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = "10 received reviews",
                            fontSize = 20.sp
                        )
                    }
                    Divider(color = Color.LightGray, thickness = 1.dp)
                    TextButton(

                        onClick = {
                            navController.navigate("${Constants.REVIEWS_SCREEN}/${userId}")
                        },
                    ) {
                        Text(modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Go to reviews",
                            color =  PrimaryColor,
                            fontSize = 18.sp
                        )
                    }
                }

            }
            Spacer(Modifier.height(14.dp))
            Card(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
                elevation = 10.dp,
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = SurfaceColor),
                ) {

                    Text(
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp),
                        text = "Await reviews",
                        fontSize = 20.sp
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                    ){
                        Icon(
                            imageVector = Icons.Filled.HourglassTop,
                            contentDescription = null,
                            tint = LightTextColor,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .size(24.dp),
                        )
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = "1 person awaits",
                            fontSize = 20.sp
                        )
                    }
                    Divider(color = Color.LightGray, thickness = 1.dp)
                    TextButton(

                        onClick = {
                            /*TODO*/
                        },
                    ) {
                        Text(modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Go to await reviews",
                            color =  PrimaryColor,
                            fontSize = 18.sp
                        )
                    }
                }

            }
            Spacer(Modifier.height(100.dp))
        }
        Divider(color = Color.LightGray, thickness = 10.dp)
    }
}


