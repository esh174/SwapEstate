package ro.greg.swapestate.presentation.chats_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.InternalCoroutinesApi
import okhttp3.internal.wait
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.presentation.chats_list.chats_components.ChatCard
import ro.greg.swapestate.presentation.components.BottomNavigationBar
import ro.greg.swapestate.presentation.components.ProgressBar

@OptIn(InternalCoroutinesApi::class)
@Composable
fun ChatsListScreen(
    navController: NavController,
    viewModel: ChatsListViewModel = hiltViewModel(),
) {
    Scaffold(

        bottomBar = {
            BottomNavigationBar(navController = navController)
        },

        ) {
        it.calculateTopPadding()

        when(val chatsResponse = viewModel.chatsState.value) {
            is Response.Loading -> ProgressBar()
            is Response.Success -> Box(
                modifier = Modifier.fillMaxSize()
            ) {

                LazyColumn {
                    items(
                        items = chatsResponse.data
                    ) { chats ->

                        ChatCard(chat = chats,navController = navController)
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

