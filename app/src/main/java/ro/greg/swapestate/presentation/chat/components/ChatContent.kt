package ro.greg.swapestate.presentation.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.shtistorm.presentation.theme.BackgroundColor
import ro.greg.shtistorm.presentation.theme.LightPrimaryColor
import ro.greg.shtistorm.presentation.theme.LightTextColor
import ro.greg.shtistorm.presentation.theme.PrimaryColor
import ro.greg.swapestate.domain.model.Message
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.presentation.chat.ChatViewModel
import ro.greg.swapestate.presentation.components.ProgressBar
import ro.greg.swapestate.presentation.search.SearchScreenViewModel

@OptIn(InternalCoroutinesApi::class)
@Composable
fun ChatContent(
    navController: NavController,
    viewModel: ChatViewModel
) {
    var message by rememberSaveable { mutableStateOf("") }
    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(LightPrimaryColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        when (val response = viewModel.messagesState.value) {
            is Response.Loading -> {
                ProgressBar()
            }
            is Response.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(weight = 0.85f, fill = true),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    reverseLayout = true
                ) {

                    items(
                        items = response.data.reversed()
                    ) { message ->
                        SingleMessage(
                            message = message,
                            isCurrentUser = viewModel.isCurrentUser(message.sentBy!!)
                        )
                    }
                }
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(weight = 0.09f, fill = true)
            .background(BackgroundColor)
        ){

            TextField(
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = BackgroundColor,
                    cursorColor = Color.Black,
                    disabledLabelColor = BackgroundColor,
                    focusedLabelColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                value = message,
                onValueChange = { message = it },
                maxLines = 1,
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 1.dp)
                    .fillMaxWidth()
                    .background(Color.Transparent),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
            label = { Text(text ="Message text") },
                singleLine = true,
                trailingIcon = {
                    IconButton(
                        enabled = !(message.isEmpty()),
                        onClick = {
                            viewModel.addMessage(message)
                            message = ""
                            
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Send Button" ,
                            tint = if (!(message.isEmpty())) PrimaryColor else LightTextColor,
                        )
                    }
                }
            )
        }

    }
}
@Composable
fun SingleMessage(message: Message, isCurrentUser: Boolean) {
    Row(
        horizontalArrangement = (if (!isCurrentUser) Arrangement.Start else Arrangement.End),
                modifier = Modifier.fillMaxWidth()
    ){
        Card(
            modifier = Modifier
                .width(((LocalConfiguration.current.screenWidthDp) / 2.3).dp)
                .padding(horizontal = 5.dp)
                .alpha((if (!isCurrentUser) 0.8 else 0.6).toFloat()),
            shape = RoundedCornerShape(16.dp),

            backgroundColor = Color.White
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()) {


                message.text?.let {
                    Text(
                        text = it,
                        textAlign =
                        if (isCurrentUser)
                            TextAlign.End
                        else
                            TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp),

                        )
                }

                message.sentOn?.let {
                    Text(

                        text = it,
                        fontSize = 10.sp,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 7.dp, end = 7.dp, top = 7.dp, bottom = 7.dp),

                        )
                }
            }
        }
    }

}