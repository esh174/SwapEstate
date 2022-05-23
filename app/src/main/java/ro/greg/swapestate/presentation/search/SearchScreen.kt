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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.swapestate.R
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.presentation.components.BottomNavigationBar
import ro.greg.swapestate.presentation.components.ProgressBar
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


@OptIn(ExperimentalMaterialApi::class, InternalCoroutinesApi::class, ExperimentalPagerApi::class)
@Composable
fun BackdropComponent( viewModel: SearchScreenViewModel = hiltViewModel()) {
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
fun RentalPager() {
    val pagerState = rememberPagerState(
        pageCount = 2
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
        ) { page ->

            BackdropComponent()

        }
    }
}

@ExperimentalPagerApi
@Composable
fun SearchImagePager() {
    val pagerState = rememberPagerState(
        pageCount = 3
    )
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        HorizontalPager(
            dragEnabled =  true,
            state = pagerState,
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
                    when (page) {
                        3 -> R.drawable.movie3
                    }
                    Image(
                        painter = painterResource(
                            id = when (page) {
                                0 -> R.drawable.movie1
                                1 -> R.drawable.movie2
                                2 -> R.drawable.movie3
                                else -> R.drawable.movie1
                            }
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