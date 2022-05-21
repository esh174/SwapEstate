package ro.greg.swapestate.presentation.rental_add.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.togitech.ccp.component.TogiCountryCodePicker
import com.togitech.ccp.data.utils.checkPhoneNumber
import com.togitech.ccp.data.utils.getDefaultLangCode
import com.togitech.ccp.data.utils.getDefaultPhoneCode
import com.togitech.ccp.data.utils.getLibCountries
import ro.greg.shtistorm.presentation.theme.LightTextColor
import ro.greg.swapestate.R
import ro.greg.swapestate.core.Constants
import ro.greg.swapestate.presentation.components.Branding
import ro.greg.swapestate.presentation.user_details.UserDetailsViewModel


@Composable
fun RentalAddContent(

    viewModel: UserDetailsViewModel = hiltViewModel()
) {

    var roomNumberField by rememberSaveable { mutableStateOf("") }
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

            verticalArrangement = Arrangement.Center,

        ) {

            Branding(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "Where is it located?"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center

                ){

                    OutlinedTextField(
                        value = roomNumberField,
                        onValueChange = {
                            roomNumberField = it
                        },
                        modifier = Modifier
                            .padding(15.dp)
                            .size(width = 60.dp, height = 50.dp),
                        textStyle = MaterialTheme.typography.body2,

                        )
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = "rooms"
                    )

                }



                Text(
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "How many rooms?"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center

                ){

                    OutlinedTextField(

                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        value = roomNumberField,
                        onValueChange = {
                            roomNumberField = it
                        },
                        modifier = Modifier
                            .padding(15.dp)
                            .size(width = 60.dp, height = 50.dp),
                        textStyle = MaterialTheme.typography.body2,

                        )
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = "rooms"
                    )

                }


                Text(
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "What its build year?"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center

                ){

                    OutlinedTextField(
                        value = roomNumberField,
                        onValueChange = {
                            roomNumberField = it
                        },
                        modifier = Modifier
                            .padding(15.dp)
                            .size(width = 60.dp, height = 50.dp),
                        textStyle = MaterialTheme.typography.body2,

                        )
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = "year"
                    )

                }







                Text(
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "Add pictures"
                )
                Box() {
                    Card(
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
                        modifier = Modifier.align(Alignment.BottomEnd).padding(5.dp).size(30.dp),
                        onClick = { launcher.launch("image/*") }
                    ) {
                        Text("+")
                    }
                }






                Button(
                    modifier = Modifier.padding(16.dp)
                        .fillMaxWidth(),
                    onClick = {

                    },
                ) {
                    Text(text = "Complete registration")
                }

            }

        }
    }
}