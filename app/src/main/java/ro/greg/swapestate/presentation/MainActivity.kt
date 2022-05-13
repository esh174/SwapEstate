package ro.greg.swapestate.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import ro.greg.swapestate.presentation.navigation.NavGraph
import ro.greg.swapestate.presentation.navigation.Screen.ProfileScreen
import ro.greg.shtistorm.presentation.theme.SwapEstateTheme

@AndroidEntryPoint
@InternalCoroutinesApi
@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAuthState()
        setContent {
            SwapEstateTheme{
                val navController = rememberAnimatedNavController()
                NavGraph(
                    navController = navController
                )
                if(viewModel.isUserAuthenticated) {
                    navController.navigate(ProfileScreen.route)
                }
            }

        }
    }
}