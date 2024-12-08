package com.example.appcalculosfinancieros.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = BlueDark,
    secondary = GrayDark,
    background = Black,
    onBackground = White
)

private val LightColorScheme = lightColorScheme(
    primary = BlueDark,
    secondary = GrayDark,
    background = GrayLight,
    onBackground = Black
)

@Composable
fun AppCalculosFinancierosTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
