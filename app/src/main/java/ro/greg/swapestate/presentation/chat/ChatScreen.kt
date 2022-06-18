package ro.greg.swapestate.presentation.chat

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.presentation.chat.components.ChatTopBar
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


                }
            }
        }
    }



}

