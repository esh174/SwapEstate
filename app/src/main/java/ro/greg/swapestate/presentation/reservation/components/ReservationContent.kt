package ro.greg.swapestate.presentation.reservation.components

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.shtistorm.presentation.theme.*
import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.domain.model.Rental
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.model.User
import ro.greg.swapestate.presentation.components.ProgressBar
import ro.greg.swapestate.presentation.reservation.ReservationViewModel
import ro.greg.swapestate.presentation.reviews.ReviewsViewModel
import ro.greg.swapestate.presentation.reviews.components.ReviewCard
import ro.greg.swapestate.presentation.search.SearchScreenViewModel
import ro.greg.swapestate.presentation.search.search_components.SearchUserCard


@OptIn(InternalCoroutinesApi::class)
@Composable
fun ReservationContent(
    navController: NavController,
    viewModel: ReservationViewModel
) {


    Box(modifier = Modifier.fillMaxSize()
        .background(color = LightPrimaryColor)){

        Column(
            modifier = Modifier
                .fillMaxWidth().verticalScroll(rememberScrollState())

            ,
        ) {
            Divider(color = Color.LightGray, thickness = 1.dp)

            Card(modifier = Modifier
                .fillMaxWidth(),
                elevation = 4.dp,
                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
            ) {
                Column() {


                    when (val response = viewModel.rentalInfoState.value) {
                        is Response.Loading -> {
                            ProgressBar()
                        }
                        is Response.Success -> {

                            response.data?.let {
                                it.userId?.let { it1 -> viewModel.getUserInfo(it1) }
                                when (val response = viewModel.userInfoState.value) {
                                    is Response.Loading -> {
                                        ProgressBar()
                                    }
                                    is Response.Success -> {
                                        response.data?.let {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .background(BackgroundColor)
                                                    .padding(vertical = 20.dp)
                                            ) {
                                                UserCard(
                                                    user = it,
                                                    navController = navController,
                                                    viewModel = viewModel
                                                )
                                            }


                                        }
                                    }
                                    else -> {}
                                }
                                Column(modifier = Modifier.padding(15.dp)) {
                                    Row(modifier = Modifier.fillMaxWidth()) {
                                        Text(it.roomNumber.toString() + "-bedroom " + it.rentalType + ", " + it.location)

                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp)
                                    ) {
                                        Row(modifier = Modifier.fillMaxWidth()) {
                                            Text(
                                                fontWeight = FontWeight.Bold,
                                                text = it.rentPrice.toString() + "$ ",
                                            )
                                            Text(
                                                text = "rent per month ",
                                            )
                                        }
                                        Row(modifier = Modifier.fillMaxWidth()) {
                                            Text(
                                                fontWeight = FontWeight.Bold,
                                                text = it.winterServicePrice.toString() + "$ ",
                                            )
                                            Text(
                                                text = "winter service ",
                                            )
                                        }
                                        Row(
                                            modifier = Modifier.fillMaxWidth()
                                                .padding(bottom = 15.dp)
                                        ) {

                                            Text(
                                                fontWeight = FontWeight.Bold,
                                                text = it.summerServicePrice.toString() + "$ ",
                                            )
                                            Text(
                                                text = "summer service ",
                                            )
                                        }
                                    }
                                }
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

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ){
                        Icon(
                            imageVector = Icons.Filled.Today,
                            contentDescription = null,
                            tint = PrimaryColor,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .size(24.dp),
                        )
                        var textStart by remember{mutableStateOf("")}
                        TextField(
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = BackgroundColor,
                                cursorColor = Color.Black,
                                disabledLabelColor = BackgroundColor,
                                focusedLabelColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            label = {Text("Choose reservation dates")},
                            value = textStart,
                            onValueChange = { textStart = it},

                        )
                    }
                    Divider(color = Color.LightGray, thickness = 1.dp)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ){
                        Icon(
                            imageVector = Icons.Filled.Event,
                            contentDescription = null,
                            tint = PrimaryColor,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .size(24.dp),
                        )
                        var textEnd by remember{mutableStateOf("")}
                        TextField(
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = BackgroundColor,
                                cursorColor = Color.Black,
                                disabledLabelColor = BackgroundColor,
                                focusedLabelColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            label = {Text("Choose reservation dates")},
                            value = textEnd,
                            onValueChange = { textEnd = it}
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

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ){
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = null,
                            tint = PrimaryColor,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .size(24.dp),
                        )
                        var textRes by remember{mutableStateOf("")}
                        TextField(
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = BackgroundColor,
                                cursorColor = Color.Black,
                                disabledLabelColor = BackgroundColor,
                                focusedLabelColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            label = {Text("Number of residents")},
                            value = textRes,
                            onValueChange = { textRes = it},


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

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top =8.dp)
                    ){
                        Icon(
                            imageVector = Icons.Filled.Pets,
                            contentDescription = null,
                            tint = PrimaryColor,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .size(24.dp),
                        )
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = "Pets",
                            fontSize = 18.sp
                        )
                    }
                    var selectedPets by remember { mutableStateOf("No") }
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Row(
                            Modifier.padding(horizontal = 6.dp).padding(start = 45.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            RadioButton(modifier = Modifier.size(14.dp),
                                selected = selectedPets == "Yes",
                                onClick = { selectedPets = "Yes" })
                            Text(
                                text = "Yes",
                                modifier = Modifier.clickable(onClick = { selectedPets = "Yes" })
                                    .padding(vertical = 8.dp).padding(horizontal = 5.dp),
                                fontSize = 14.sp
                            )
                        }
                        Row(
                            Modifier.padding(horizontal = 6.dp).padding(start = 15.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            RadioButton(modifier = Modifier.size(14.dp),
                                selected = selectedPets == "No",
                                onClick = { selectedPets = "No" },
                            )
                            Text(
                                text = "No",
                                modifier = Modifier.clickable(onClick = { selectedPets = "No" })
                                    .padding(vertical = 8.dp).padding(horizontal = 5.dp)
                            )

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

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding( top = 8.dp)
                    ){
                        Icon(
                            imageVector = Icons.Filled.ChildCare,
                            contentDescription = null,
                            tint = PrimaryColor,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .size(24.dp),
                        )
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = "Kids",
                            fontSize = 18.sp
                        )
                    }
                    var selectedKids by remember { mutableStateOf("No") }
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Row(
                            Modifier.padding(horizontal = 6.dp).padding(start = 45.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            RadioButton(modifier = Modifier.size(14.dp),
                                selected = selectedKids == "Yes",
                                onClick = { selectedKids = "Yes" })
                            Text(
                                text = "Yes",
                                modifier = Modifier.clickable(onClick = { selectedKids = "Yes" })
                                    .padding(vertical = 8.dp).padding(horizontal = 5.dp),
                                fontSize = 14.sp
                            )
                        }
                        Row(
                            Modifier.padding(horizontal = 6.dp).padding(start = 15.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            RadioButton(
                                modifier = Modifier.size(14.dp),
                                selected = selectedKids == "No",
                                onClick = { selectedKids = "No" })
                            Text(
                                text = "No",
                                modifier = Modifier.clickable(onClick = { selectedKids = "No" })
                                    .padding(vertical = 8.dp).padding(horizontal = 5.dp),
                                fontSize = 14.sp
                            )

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

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding( top = 8.dp)
                    ){
                        Icon(
                            imageVector = Icons.Filled.Comment,
                            contentDescription = null,
                            tint = PrimaryColor,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .size(24.dp),
                        )
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = "Message",
                            fontSize = 18.sp
                        )
                    }
                    var message by remember { mutableStateOf("") }
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Column(Modifier.fillMaxWidth().padding(horizontal =30.dp)
                            .padding(bottom =30.dp),){
                            TextField(
                                colors = TextFieldDefaults.textFieldColors(
                                    cursorColor = Color.Black,
                                    disabledLabelColor = BackgroundColor,
                                    focusedLabelColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                label = {Text("Message text")},
                                value = message,
                                onValueChange = { message = it},

                                )

                        }

                        }


                    }
                }
            Spacer(Modifier.height(14.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {

                    navController.navigateUp()
                },
            ) {
                Text(text = "Send request")
            }
            Spacer(Modifier.height(80.dp))
            }

        }
    }




@OptIn(ExperimentalMaterialApi::class)
@Composable
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
fun UserCard(
    navController : NavController,
    user: User,
    viewModel: ReservationViewModel
) {


    viewModel.getProfileImageUrl(user.id!!)
    val profileImageUrlState = viewModel.getProfileImageUrlState

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp).padding(top = 10.dp)){
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(15.dp)
                .size(100.dp),
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
                                .size(18.dp)
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

