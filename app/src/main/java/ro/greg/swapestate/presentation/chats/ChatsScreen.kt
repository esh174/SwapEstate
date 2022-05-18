package ro.greg.swapestate.presentation.chats

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ro.greg.swapestate.presentation.components.BottomNavigationBar

@Composable
fun ChatsScreen(
    navController: NavController,
) {
    Scaffold(

        bottomBar = {
            BottomNavigationBar(navController = navController)
        },

        ) {
        it.calculateTopPadding()
        Text(
            modifier = Modifier.padding(48.dp),
            text = "Chats",
            fontSize = 24.sp
        )
    }
}

