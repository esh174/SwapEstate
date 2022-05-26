package ro.greg.swapestate.presentation.search

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import ro.greg.shtistorm.presentation.theme.LightTextColor
import ro.greg.shtistorm.presentation.theme.PrimaryColor
import ro.greg.swapestate.R
import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.core.Utils
import ro.greg.swapestate.domain.model.Rental
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.presentation.components.BottomNavigationBar
import ro.greg.swapestate.presentation.components.ProgressBar
import ro.greg.swapestate.presentation.navigation.Screen
import ro.greg.swapestate.presentation.profile.components.ProfileCard
import ro.greg.swapestate.presentation.search.search_components.SearchUserCard
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SearchScreen(
    navController: NavController,
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        it.calculateTopPadding()
        RentalPager()

    }

}



@ExperimentalPagerApi
@Composable
fun RentalPager(
    viewModel: SearchScreenViewModel = hiltViewModel(),
) {

    when (val response = viewModel.rentalsState.value) {
        is Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {
            val parentPagerState = rememberPagerState(
                pageCount = response.data.count()
            )

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                HorizontalPager(
                    state = parentPagerState,
                    modifier = Modifier
                        .weight(1f)
                ) { page ->
//                    Row(Modifier.fillMaxSize()){
//                        Text(page.toString())
//                    }

                        BackdropComponent(response.data.elementAt(parentPagerState.currentPage))



                }
            }


        }
    }
}

@OptIn(ExperimentalMaterialApi::class,
    InternalCoroutinesApi::class,
    ExperimentalPagerApi::class)
@Composable
fun BackdropComponent(
    rental: Rental,
    viewModel: SearchScreenViewModel = hiltViewModel(),
) {
    rememberSystemUiController().isStatusBarVisible = false //status bar visible flag
    val backdropState = rememberBackdropScaffoldState(initialValue = BackdropValue.Concealed)

    LaunchedEffect(backdropState) {
        backdropState.reveal()
    }
    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 50f)
    }

    val offset by backdropState.offset
    val halfHeightDp = LocalConfiguration.current.screenHeightDp / 4
    val halfHeightPx = with(LocalDensity.current) {
        halfHeightDp.dp.toPx()
    }
    viewModel.getUserInfo(rental.userId!!)
    val userInfoState = viewModel.userInfoState
    BackdropScaffold(appBar = { },
        scaffoldState = backdropState,
        frontLayerScrimColor = Color.Unspecified,
        peekHeight = 100.dp,
        backLayerBackgroundColor = Color.White,
        headerHeight = halfHeightDp.dp,
        backLayerContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .alpha(offset / halfHeightPx)
            ) {

                SearchImagePager(rental.id!!)
            }
        },
        frontLayerContent = {
            Column(

                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier
                        .height(11.dp)
                        .width(101.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .padding(vertical = 5.dp)
                        .background(Color.LightGray),

                    ) {
                }


                when (val response = userInfoState.value) {
                    is Response.Loading -> {
                        ProgressBar()
                    }
                    is Response.Success -> {
                        response.data?.let {
                            SearchUserCard(
                                user = it
                            )
                        }
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(rental.roomNumber.toString() + "-bedroom " + rental.rentalType + ", " + rental.location)

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = rental.rentPrice.toString() + "$ ",
                        )
                        Text(
                            text = "rent per month ",
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = rental.winterServicePrice.toString() + "$ ",
                        )
                        Text(
                            text = "winter service ",
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {

                        Text(
                            fontWeight = FontWeight.Bold,
                            text = rental.summerServicePrice.toString() + "$ ",
                        )
                        Text(
                            text = "summer service ",
                        )
                    }
                }


                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                    )
                    {

                        Text(
                            text = "Construction year: ",
                        )

                        Text(
                            fontWeight = FontWeight.Bold,
                            text = rental.buildYear.toString(),
                        )
                    }

                    Row(modifier = Modifier
                        .fillMaxWidth()
                    )
                    {

                        Text(
                            text = "Number of floors: ",
                        )

                        Text(
                            fontWeight = FontWeight.Bold,
                            text = rental.floorsNumber.toString(),
                        )
                    }
                }

                val singapore = LatLng(1.35, 103.87)
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(singapore, 10f)
                }
                Box(
                    modifier = Modifier
                        .size(width = 300.dp, height = 180.dp)
                        .padding(15.dp),
                ) {
                    GoogleMap(
                        uiSettings = MapUiSettings(
                            myLocationButtonEnabled = false,
                            zoomGesturesEnabled = false,
                            rotationGesturesEnabled = false,
                            compassEnabled = false,
                            zoomControlsEnabled = false,
                            tiltGesturesEnabled = false
                        ),
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState,

                        ) {
                        Marker(
                            state = MarkerState(position = singapore)
                        )
                    }
                }



                Column(modifier = Modifier
                    .fillMaxWidth()
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {

                        if (rental.isAnimalsAllowed == true) {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "isAnimalsAllowed",
                                tint = PrimaryColor,
                                modifier = Modifier.padding(3.dp)
                            )
                            Text(
                                text = " animals are allowed",
                            )
                        } else  {
                            Icon(
                                imageVector = Icons.Filled.DisabledByDefault,
                                contentDescription = "isAnimalsAllowed",
                                tint = LightTextColor,
                                modifier = Modifier.padding(3.dp)
                            )

                            Text(
                                text = " animals are NOT allowed",
                            )
                        }
                    }

                    Row(modifier = Modifier
                        .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {

                        if (rental.isKidsAllowed == true) {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "isKidsAllowed",
                                tint = PrimaryColor,
                                modifier = Modifier.padding(3.dp)
                            )
                            Text(
                                text = " kids are allowed",
                            )

                        } else {

                            Icon(
                                imageVector = Icons.Filled.DisabledByDefault,
                                contentDescription = "isKidsAllowed",
                                tint = LightTextColor,
                                modifier = Modifier.padding(3.dp)
                            )

                            Text(
                                text = " kids are NOT allowed",
                            )
                        }
                    }
                    Column(modifier = Modifier
                       .fillMaxWidth()) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = "Comment:",
                        )
                        rental.comment?.let {
                            Text(
                                text = it)
                        }
                    }

                    Column(modifier = Modifier
                        .fillMaxWidth()){
                        Row(modifier = Modifier
                            .fillMaxWidth()){
                            TextButton(
                                modifier = Modifier.height(50.dp).width(((LocalConfiguration.current.screenWidthDp)/2).dp),
                                onClick = {
                                },
                            ) {
                                Text(
                                    text = "Chat",
                                    color =  PrimaryColor,
                                    fontSize = 32.sp
                                )
                            }
                            TextButton(
                                modifier = Modifier.height(50.dp).width(((LocalConfiguration.current.screenWidthDp)/2).dp),
                                onClick = {
                                },
                            ) {
                                Text(
                                    text = "Call",
                                    color =  PrimaryColor,
                                    fontSize = 32.sp
                                )
                            }
                        }
                        TextButton(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                            onClick = {
                            },
                        ) {
                            Text(

                                text = "Reserve",
                                color =  Color.Black,
                                fontSize = 32.sp
                            )
                        }
                    }

                    Spacer(Modifier.height(100.dp))


                }

            }
        }
    )
}



@ExperimentalPagerApi
@Composable
fun SearchImagePager(
    rentalId: String,
    viewModel: SearchScreenViewModel = hiltViewModel(),) {
   val pagerState2 = rememberPagerState(
        pageCount = 2
    )
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        HorizontalPager(
            dragEnabled =  true,
            state = pagerState2,
            modifier = Modifier
                .weight(1f)
        ) { page ->
//            viewModel.getRentalImageUrl(rentalId  = rentalId, page = pagerState2.currentPage)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                        .align(Alignment.Center)
                ) {

                    when (val getRentalImageUrlResponse = viewModel.getRentalImageUrlState.value) {
                        is Response.Loading -> {
                            ProgressBar()
                        }
                        is Response.Success -> {
                            Image(
                                painter = rememberImagePainter(
                                    data = getRentalImageUrlResponse.data
                                ),
                                contentDescription = "Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }





                    }
                }
            }
        }
    }

@OptIn(ExperimentalCoilApi::class)
@Composable
fun getImageResource(index: Int) = when (index) {
    0 -> Image(
        painter = rememberImagePainter(
            data = R.drawable.movie3

        ),
        contentDescription = "Image",
    )
    1 -> R.drawable.movie2
    2 -> R.drawable.movie3
    else -> R.drawable.movie1
}
