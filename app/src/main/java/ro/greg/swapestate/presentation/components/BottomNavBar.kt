package ro.greg.swapestate.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ro.greg.shtistorm.presentation.theme.BackgroundColor
import ro.greg.shtistorm.presentation.theme.LightTextColor
import ro.greg.shtistorm.presentation.theme.PrimaryColor
import ro.greg.swapestate.core.Constants.CHATS_SCREEN
import ro.greg.swapestate.core.Constants.PROFILE_SCREEN
import ro.greg.swapestate.core.Constants.SEARCH_SCREEN
import ro.greg.swapestate.presentation.profile.ProfileViewModel

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    BottomNavigationBar(
        items = listOf(
            BottomNavItem(
                name = "Chat",
                route = CHATS_SCREEN,
                icon = Icons.Outlined.Chat
            ),
            BottomNavItem(
                name = "Search",
                route = SEARCH_SCREEN,
                icon = Icons.Outlined.Search,
                badgeCount = 23
            ),
            BottomNavItem(
                name = "Profile",
                route = PROFILE_SCREEN,
                icon = Icons.Outlined.AccountCircle,
                badgeCount = 214
            ),
        ),
        navController = navController,                onItemClick = {
            navController.navigate(it.route)
        }
    )
}


@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = BackgroundColor,
        elevation = 5.dp
    ) {

        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = PrimaryColor,
                unselectedContentColor = LightTextColor,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if(item.badgeCount > 0) {
                            BadgedBox(badge = { Badge { Text(text = item.badgeCount.toString()) } }) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name
                                )
                            }
                        } else {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name
                            )
                        }
                        if(selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            )
        }
    }
}