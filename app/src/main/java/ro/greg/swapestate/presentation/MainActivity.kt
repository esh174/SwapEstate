package ro.greg.swapestate.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.view.WindowCompat
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.shtistorm.presentation.theme.SwapEstateTheme
import ro.greg.swapestate.presentation.navigation.NavGraph
import ro.greg.swapestate.presentation.navigation.Screen.ProfileScreen

@AndroidEntryPoint
@InternalCoroutinesApi
@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        viewModel.getAuthState()
        setContent {
            SwapEstateTheme {
                val navController = rememberAnimatedNavController()
                NavGraph(
                    navController = navController
                )
                if (viewModel.isUserAuthenticated) {
                    navController.popBackStack()
                    navController.navigate(ProfileScreen.route)
                }
            }
        }
    }
}