package ro.greg.swapestate.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.shtistorm.presentation.theme.PrimaryColor
import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.domain.model.Rental
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.presentation.components.BottomNavigationBar
import ro.greg.swapestate.presentation.components.ProgressBar
import ro.greg.swapestate.presentation.search.search_components.SearchUserCard

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

        RentalPager(navController = navController)
    }

}

@ExperimentalPagerApi
@Composable
fun RentalPager(
    navController: NavController,
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
                    BackdropComponent(
                        rental = response.data.elementAt(parentPagerState.currentPage),
                        navController = navController
                    )

                }
            }
        }
    }
}

@OptIn(
    ExperimentalMaterialApi::class,
    InternalCoroutinesApi::class,
    ExperimentalPagerApi::class
)
@Composable
fun BackdropComponent(
    rental: Rental,
    navController: NavController,
    viewModel: SearchScreenViewModel = hiltViewModel(),
) {
    viewModel.getRentalImagesUrl(rentalId = rental.id!!, count = rental.imagesNumber!!)
    val backdropState = rememberBackdropScaffoldState(initialValue = BackdropValue.Concealed)

    LaunchedEffect(backdropState) {
        backdropState.reveal()
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
                    .background(Color.Transparent)
                    .alpha(offset / halfHeightPx)
            ) {
                SearchImagePager(rental = rental)
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
                                user = it,
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(rental.roomNumber.toString() + "-year old " + rental.rentalType + ", " + rental.location)

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = rental.rentPrice.toString() + "$ ",
                        )
                        Text(
                            text = "for mating meeting ",
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = rental.winterServicePrice.toString() + "$ ",
                        )
                        Text(
                            text = "kitten price",
                        )
                    }
                }


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    {
                        Text(
                            text = "Birth date: ",
                        )

                        Text(
                            fontWeight = FontWeight.Bold,
                            text = rental.buildYear.toString(),
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Number of won prizes: ",
                        )
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = rental.floorsNumber.toString(),
                        )
                    }
                }

                val singapore = LatLng(1.35, 103.87)
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(singapore, 50f)
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = "Comment:",
                        )
                        rental.comment?.let {
                            Text(
                                text = it
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            TextButton(
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(((LocalConfiguration.current.screenWidthDp) / 2).dp),
                                onClick = {
                                    navController.navigate(
                                        "${Constants.CHAT_SCREEN}/${
                                            viewModel.getChatId(
                                                rental = rental
                                            )
                                        }"
                                    )
                                },
                            ) {
                                Text(
                                    text = "Chat",
                                    color = PrimaryColor,
                                    fontSize = 32.sp
                                )
                            }
                            TextButton(
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(((LocalConfiguration.current.screenWidthDp) / 2).dp),
                                onClick = {
                                },
                            ) {
                                Text(
                                    text = "Call",
                                    color = PrimaryColor,
                                    fontSize = 32.sp
                                )
                            }
                        }
                        TextButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp),
                            onClick = {
                                navController.navigate("${Constants.RESERVATION_SCREEN}/${rental.id}")
                            },
                        ) {
                            Text(

                                text = "Reserve",
                                color = Color.Black,
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
    viewModel: SearchScreenViewModel = hiltViewModel(),
    rental: Rental
) {
    val pagerState2 = rememberPagerState(
        pageCount = rental.imagesNumber!!
    )
    when (val getRentalImagesUrlResponse = viewModel.getRentalImagesUrlState.value) {
        is Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                HorizontalPager(
                    dragEnabled = true,
                    state = pagerState2,
                    modifier = Modifier
                        .weight(1f)
                ) { page ->
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
                            AsyncImage(
                                model = getRentalImagesUrlResponse.data.elementAt(page),
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






