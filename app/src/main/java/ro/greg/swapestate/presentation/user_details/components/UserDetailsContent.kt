package ro.greg.swapestate.presentation.user_details.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
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
fun UserDetailsContent(
    viewModel: UserDetailsViewModel = hiltViewModel()
) {
    val getDefaultLangCode = getDefaultLangCode() // Auto detect language
    val getDefaultPhoneCode = getDefaultPhoneCode() // Auto detect phone code : +7
    var phoneCode by rememberSaveable { mutableStateOf(getDefaultPhoneCode) }
    val phoneNumber = rememberSaveable { mutableStateOf("") }
    var defaultLang by rememberSaveable { mutableStateOf(getDefaultLangCode) }
    var isValidPhone by remember { mutableStateOf(true) }
    var userName by remember { mutableStateOf("") }
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
                        modifier = Modifier.align(Alignment.BottomEnd).padding(5.dp).size(30.dp),
                        onClick = { launcher.launch("image/*") }
                    ) {
                        Text("+")
                    }
                }

                Text(
                    fontWeight = FontWeight.Bold,
                    text = "Enter your name",
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp)
                )
                OutlinedTextField(
                    value = userName,
                    onValueChange = {
                        userName = it
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
                    text = "Enter your phone number",
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp)
                )
                TogiCountryCodePicker(
                    modifier = Modifier.fillMaxWidth(),
                    pickedCountry = {
                        phoneCode = it.countryPhoneCode
                        defaultLang = it.countryCode
                    },
                    defaultCountry = getLibCountries().single { it.countryCode == defaultLang },
                    focusedBorderColor = MaterialTheme.colors.primary,
                    unfocusedBorderColor = LightTextColor,
                    dialogAppBarTextColor = Color.Black,
                    dialogAppBarColor = Color.White,
                    error = isValidPhone,
                    text = phoneNumber.value,
                    onValueChange = { phoneNumber.value = it }
                )

                val fullPhoneNumber = "$phoneCode${phoneNumber.value}"
                val checkPhoneNumber = checkPhoneNumber(
                    phone = phoneNumber.value,
                    fullPhoneNumber = fullPhoneNumber,
                    countryCode = defaultLang
                )


                var selected by remember { mutableStateOf("Renter") }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Row(
                        Modifier.padding(horizontal = 30.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = selected == "Renter",
                            onClick = { selected = "Renter" })
                        Text(
                            text = "Renter",
                            modifier = Modifier.clickable(onClick = { selected = "Renter" })
                                .padding(vertical = 8.dp)
                        )
                    }
                    Row(
                        Modifier.padding(horizontal = 30.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = selected == "Owner",
                            onClick = { selected = "Owner" })
                        Text(
                            text = "Owner",
                            modifier = Modifier.clickable(onClick = { selected = "Owner" })
                                .padding(vertical = 8.dp)
                        )

                    }


                }


                Button(
                    modifier = Modifier.padding(16.dp)
                        .fillMaxWidth(),
                    onClick = {
                        viewModel.addUserInfo(
                            userName,
                            fullPhoneNumber,
                            Uri.parse(imageUri),
                            selected
                        )
                    },
                ) {
                    Text(text = Constants.CONTINUE)
                }

            }

        }
    }
}