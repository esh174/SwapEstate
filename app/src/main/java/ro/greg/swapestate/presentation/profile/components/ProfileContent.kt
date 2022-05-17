package ro.greg.swapestate.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import ro.greg.swapestate.domain.model.Response
import ro.greg.shtistorm.presentation.theme.LightPrimaryColor
import ro.greg.shtistorm.presentation.theme.LightTextColor
import ro.greg.shtistorm.presentation.theme.SurfaceColor
import ro.greg.swapestate.R
import ro.greg.swapestate.core.Constants.WELCOME_MESSAGE
import ro.greg.swapestate.presentation.components.ProgressBar

@Composable
fun ProfileContent(
    profileImageUrlState: State<Response<String>>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightPrimaryColor),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = SurfaceColor)

        ) {

            Card(
                shape = CircleShape,
                modifier = Modifier
                    .padding(8.dp)
                    .size(100.dp),
                elevation = 10.dp,

                ) {
                when(val response = profileImageUrlState.value){
                    is Response.Loading -> {
                        ProgressBar()
//                        Image(
//                            painter = rememberImagePainter(
//                                data = R.drawable.ic_user
//                            ),
//                            contentDescription = null,
//                            modifier = Modifier
//                                .wrapContentSize(),
//                            contentScale = ContentScale.Crop
//                        )
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

            }

            Text(
                modifier = Modifier.padding(48.dp),
                text = WELCOME_MESSAGE,
                fontSize = 24.sp
            )

        }

}
