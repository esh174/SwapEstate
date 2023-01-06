package ro.greg.swapestate.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ro.greg.swapestate.R


@Composable
fun Logo(
    modifier: Modifier = Modifier,
    lightTheme: Boolean = MaterialTheme.colors.isLight
) {
    val assetId = if (lightTheme) {
        R.drawable.meetpet
    } else {
        R.drawable.meetpet
    }
    Image(

        painter = painterResource(id = assetId),
        modifier = modifier,
        contentDescription = null
    )
}
