package ro.greg.shtistorm.presentation.theme

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = PrimaryColor,
    primaryVariant = PrimaryColor,
    secondary = PrimaryColor,
    background = BackgroundColor,
    onSurface = OnSurfaceColor,
    surface = SurfaceColor,
)

@Composable
fun SwapEstateTheme(
    content: @Composable() () -> Unit
) {
    val colors = LightColorPalette
    rememberSystemUiController().setSystemBarsColor(
        color = Color.Transparent,
        darkIcons = true
    )
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}