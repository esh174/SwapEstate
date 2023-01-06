package ro.greg.swapestate.presentation.planned_message_template

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.swapestate.presentation.planned_message_template.components.PlannedMessageTemplateTopBar
import ro.greg.swapestate.presentation.planned_messages.components.PlannedMessagesTopBar
import ro.greg.swapestate.presentation.auth.sign_up.SignUpViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import ro.greg.shtistorm.presentation.theme.BackgroundColor

@Composable
@InternalCoroutinesApi
fun PlannedMessageTemplateScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            PlannedMessageTemplateTopBar(navController = navController)
        }
    ) {
        it.calculateTopPadding()
        Column(
            modifier = Modifier
                .fillMaxWidth().padding(15.dp)
        ) {


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 0.8f)

            ) {

                dropDownMenu()
                var text by remember { mutableStateOf("") }
                var serviceText by remember { mutableStateOf("") }
                var renovationText by remember { mutableStateOf("") }
                var codeText by remember { mutableStateOf("") }
                OutlinedTextField(
                    label = {Text("Messages")},
                    value = text,
                    onValueChange = { text = it},
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),


                )

                var servicesSelected by remember{mutableStateOf(false)}
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier= Modifier.fillMaxWidth()
                ){
                    RadioButton(
                        selected = servicesSelected == true,
                        onClick = { servicesSelected = !servicesSelected
                             })
                    Text(
                        text = "Services description",
                        modifier = Modifier.clickable(onClick = { servicesSelected =
                            !servicesSelected
                        })
                            .padding(vertical = 8.dp)
                    )
                }

                OutlinedTextField(
                    enabled = servicesSelected,
                    label = {Text("Services description")},
                    value = serviceText,
                    onValueChange = { serviceText = it},
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),

                    )


                var renovationSelected by remember{mutableStateOf(false)}
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier= Modifier.fillMaxWidth()
                ){
                    RadioButton(
                        selected = renovationSelected,
                        onClick = { renovationSelected = !renovationSelected })
                    Text(
                        text = "Renovation availability",
                        modifier = Modifier.clickable(onClick = { renovationSelected = !renovationSelected  })
                            .padding(vertical = 8.dp)
                    )
                }

                OutlinedTextField(
                    enabled = renovationSelected,
                    label = {Text("Renovation availability")},
                    value = renovationText,
                    onValueChange = { renovationText = it},
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),


                    )
                var codeSelected by remember{mutableStateOf(false)}
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier= Modifier.fillMaxWidth()
                ){
                    RadioButton(
                        selected = codeSelected,
                        onClick = { codeSelected = !codeSelected })
                    Text(
                        text = "Keys/code",
                        modifier = Modifier.clickable(onClick = { codeSelected = !codeSelected  })
                            .padding(vertical = 8.dp)
                    )
                }

                OutlinedTextField(
                    enabled = codeSelected,
                    label = {Text("Keys/code")},
                    value = codeText,
                    onValueChange = { codeText = it},
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),


                    )


            }
            Column(
                modifier = Modifier.weight(weight = 0.09f),
                verticalArrangement = Arrangement.Bottom){
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {

                        //navController.navigate(Constants.USER_DETAILS_SCREEN)
                    },
                ) {
                    Text(text = "Save template")
                }
            }



        }

    }
}


@Composable
fun dropDownMenu() {

    var expanded by remember { mutableStateOf(false) }
    val suggestions =
        listOf("Chat start", "Reservation confirmed", "Start of lease", "End of lease",)
    var selectedText by remember { mutableStateOf("Start of lease") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(Modifier.padding(15.dp)) {
        TextField(

            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = BackgroundColor,
                cursorColor = Color.Black,
                disabledLabelColor = Color.Transparent,
                focusedLabelColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                trailingIconColor = Color.Black,
                unfocusedIndicatorColor = Color.Transparent),
            value = selectedText,

            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },

            textStyle = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,),
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    expanded = false
                }) {
                    Text(
                        text = label)
                }
            }
        }
    }
}