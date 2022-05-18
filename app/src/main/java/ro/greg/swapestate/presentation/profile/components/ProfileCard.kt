package ro.greg.swapestate.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.model.User
import ro.greg.swapestate.presentation.components.ProgressBar
import ro.greg.swapestate.presentation.profile.ProfileViewModel


@Composable
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
fun ProfileCard(
    user: User,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val profileImageUrlState = viewModel.getProfileImageUrlState
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 15.dp, vertical = 20.dp)){
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(15.dp)
                .size(130.dp),
            elevation = 0.dp,

            ) {
            when (val response = profileImageUrlState.value) {
                is Response.Loading -> {
                    ProgressBar()
                }
                is Response.Success -> {
                    Image(

                        painter = rememberImagePainter(
                            data = response.data
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .wrapContentSize(),
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
                        fontSize = 32.sp

                    )
                }
                user.rating.let { rating ->
                    Text(
                        text = rating.toString(),
                        fontSize = 25.sp
                    )
                }
                user.reviewsNumber.let { rating ->
                    val reviewsString = if(rating%10 == 1) "review" else "reviews"
                    Text(

                        text = "$rating $reviewsString",
                        fontSize = 18.sp
                    )
                }

            }
        }
    }

}

