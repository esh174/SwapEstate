package ro.greg.swapestate.presentation.search

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ro.greg.swapestate.presentation.components.BottomNavigationBar

@Composable
fun SearchScreen(
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
            text = "Search",
            fontSize = 24.sp
        )
    }
}
