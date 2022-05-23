package ro.greg.swapestate.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import ro.greg.swapestate.R
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
    viewModel: SearchScreenViewModel = hiltViewModel(),
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

    val offset by backdropState.offset
    val halfHeightDp = LocalConfiguration.current.screenHeightDp / 4
    val halfHeightPx = with(LocalDensity.current) {
        halfHeightDp.dp.toPx()
    }
    viewModel.getUserInfo(rental.userId!!)
    val userInfoState = viewModel.userInfoState
    BackdropScaffold(
        appBar = {        },
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

                SearchImagePager()
            }
        },
        frontLayerContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {


                //getProfileImageUrl()


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

            }
        }
    )
}



@ExperimentalPagerApi
@Composable
fun SearchImagePager() {
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


                    Image(
                        painter = painterResource(
                            id = getImageResource(page)
                        ),
                        contentDescription = "Image",
                        contentScale = ContentScale.Inside,
                        modifier = Modifier.fillMaxSize()
                    )



                    }
                }
            }
        }
    }


private fun getImageResource(index: Int) = when (index) {
    0 -> R.drawable.movie1
    1 -> R.drawable.movie2
    2 -> R.drawable.movie3
    else -> R.drawable.movie1
}
