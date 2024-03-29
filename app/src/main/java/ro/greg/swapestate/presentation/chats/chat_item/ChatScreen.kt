package ro.greg.swapestate.presentation.chats.chat_item

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.presentation.chats.chat_item.components.ChatContent
import ro.greg.swapestate.presentation.chats.chat_item.components.ChatTopBar
import ro.greg.swapestate.presentation.components.ProgressBar


@OptIn(InternalCoroutinesApi::class)
@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel(),
    navController: NavController
) {
    when (val response = viewModel.getChatState.value) {
        is Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {
            response.data?.let { it ->
                Scaffold(
                    modifier = Modifier.systemBarsPadding(),
                    topBar = { ChatTopBar(navController = navController, viewModel = viewModel, chat = it) }
                ) {

                    it.calculateTopPadding()
                    Divider(color = Color.LightGray, thickness = 1.dp)
                    ChatContent(viewModel = viewModel,navController = navController )
                }
            }
        }
    }



}

