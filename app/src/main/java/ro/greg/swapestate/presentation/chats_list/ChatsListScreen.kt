package ro.greg.swapestate.presentation.chats_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import okhttp3.internal.wait
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.presentation.chats_list.chats_components.ChatCard
import ro.greg.swapestate.presentation.chats_list.chats_components.ChatListTopBar
import ro.greg.swapestate.presentation.components.BottomNavigationBar
import ro.greg.swapestate.presentation.components.ProgressBar

@OptIn(InternalCoroutinesApi::class)
@Composable
fun ChatsListScreen(
    navController: NavController,
    viewModel: ChatsListViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = { ChatListTopBar(navController = navController, viewModel = viewModel) },
        modifier = Modifier.systemBarsPadding(),
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },

        ) {
        it.calculateTopPadding()
        Divider(color = Color.LightGray, thickness = 1.dp)
        when(val chatsResponse = viewModel.chatsState.value) {
            is Response.Loading -> ProgressBar()
            is Response.Success -> Box(
                modifier = Modifier.fillMaxSize()
            ) {

                LazyColumn {
                    items(
                        items = chatsResponse.data
                    ) { chat ->
                        System.currentTimeMillis()
                        ChatCard(chat = chat,navController = navController)
                    }
                }
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                }
            }
        }





    }
}

