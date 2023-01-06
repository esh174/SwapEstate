package ro.greg.swapestate.presentation.search.search_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.presentation.components.ProgressBar
import ro.greg.swapestate.presentation.profile.ProfileViewModel


@Composable
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
fun SearchRentalCard(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val profileImageUrlState = viewModel.getProfileImageUrlState
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 20.dp)
    ) {
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(15.dp)
                .size(80.dp),
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
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)) {
            }
        }
    }
}

