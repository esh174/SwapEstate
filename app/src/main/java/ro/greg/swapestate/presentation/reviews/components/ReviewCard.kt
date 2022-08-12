package ro.greg.swapestate.presentation.reviews.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SubdirectoryArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.shtistorm.presentation.theme.PrimaryColor
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.model.Review
import ro.greg.swapestate.presentation.components.ProgressBar
import ro.greg.swapestate.presentation.reviews.ReviewsViewModel


@Composable
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
 fun ReviewCard(
    review: Review,
    viewModel: ReviewsViewModel
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        elevation = 10.dp,

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth().padding(horizontal = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth().padding(vertical = 10.dp)
            ) {

                Card(

                    modifier = Modifier
                        .size(70.dp),
                    shape = CircleShape,
                    elevation = 0.dp,

                    ) {

                    Image(

                        painter = rememberAsyncImagePainter(
                            model = viewModel.cloudStorageGetImageUrl(review.userId!!)

                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(),

                        contentScale = ContentScale.Crop

                    )
                }


                review.userName?.let {
                    Text(

                        modifier = Modifier
                            .padding(horizontal = 10.dp),
                        text = it,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End
                    )
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
                    review.rentalLocation?.let {
                        Text(
                            text = it
                        )
                    }
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

            val textRating = when (review.rating) {
                1 -> "Poor"
                2 -> "Fair"
                3 -> "Good"
                4 -> "Very Good"
                5 -> "Excellent"
                else -> ""
            }
            Text(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                .padding(start = 10.dp)
                .padding(vertical = 15.dp),
                text = textRating
            )




            review.text?.let {
                Text(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .padding(bottom = 15.dp),
                    text = it
                )
            }

        }

    }
}






