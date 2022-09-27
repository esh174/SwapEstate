@file:OptIn(ExperimentalAnimationApi::class, ExperimentalAnimationApi::class,
    ExperimentalAnimationApi::class
)

package ro.greg.swapestate.presentation.auth

import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertIsFocused
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*
import org.junit.Rule
import ro.greg.swapestate.di.AppModule
import ro.greg.swapestate.presentation.MainActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Before
import org.junit.Test
import ro.greg.shtistorm.presentation.theme.SwapEstateTheme
import ro.greg.swapestate.core.TestTags
import ro.greg.swapestate.presentation.navigation.NavGraph
import ro.greg.swapestate.presentation.navigation.Screen

@OptIn(InternalCoroutinesApi::class)
@HiltAndroidTest
@UninstallModules(AppModule::class)
class AuthScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            SwapEstateTheme {
                val navController = rememberAnimatedNavController()
                AnimatedNavHost(
                    navController = navController,
                    startDestination = Screen.AuthScreen.route,
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None }
                ) {
                    composable(
                        route = Screen.AuthScreen.route
                    ) {
                        AuthScreen(
                            navController = navController
                        )
                    }
                }
            }
        }
    }
    @Test
    fun clickEmailEmptyBoxError_isVisible() {
        composeRule.onNodeWithTag(TestTags.EMAIL_TAG).performClick()
        composeRule.onNodeWithTag(TestTags.EMAIL_TAG).assertIsFocused()
    }
}