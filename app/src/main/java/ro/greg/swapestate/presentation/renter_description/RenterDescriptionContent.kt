package ro.greg.swapestate.presentation.renter_description

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import ro.greg.swapestate.R
import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.presentation.user_details.UserDetailsViewModel



@Composable
fun RenterDescriptionContent(
    viewModel: RenterDescriptionViewModel = hiltViewModel()
) {
    var name by rememberSaveable { mutableStateOf("WxnHZuxar7ixfDXwmsOM") }
    var imageUri by rememberSaveable { mutableStateOf("") }
    val painter = rememberImagePainter(
        if (imageUri.isEmpty())
            R.drawable.ic_user
        else
            imageUri
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri = it.toString() }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .absolutePadding(bottom = 15.dp, top = 15.dp, left = 15.dp, right = 15.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    label = {
                        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                            Text(
                                text = "Name",
                                style = MaterialTheme.typography.body2
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    textStyle = MaterialTheme.typography.body2,

                    )

                Text(
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "Add profile picture"
                )
                Box() {
                    Card(
                        shape = CircleShape,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(100.dp),
                        elevation = 10.dp,

                        ) {
                        Image(

                            painter = painter,
                            contentDescription = null,
                            modifier = Modifier
                                .wrapContentSize()
                                .clickable { launcher.launch("image/*") },
                            contentScale = ContentScale.Crop
                        )
                    }
                    FloatingActionButton(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(5.dp)
                            .size(30.dp),
                        onClick = { launcher.launch("image/*") }
                    ) {
                        Text("+")
                    }
                }









                Button(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    onClick = {
                       viewModel.addUserInfo(imageUri = Uri.parse(imageUri), name = name)

                    },
                ) {
                    Text(text = Constants.CONTINUE)
                }

            }

        }
    }
}