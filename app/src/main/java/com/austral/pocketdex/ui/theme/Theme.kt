package com.austral.pocketdex.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

data class ExtendedColors(
    val lightPrimary: Color,
    val lightSecondary: Color,
//    val bottomBarSelected: Color,
//    val bottomBarUnselected: Color
)

private val DarkExtendedColors = ExtendedColors(
    lightPrimary = Orange70,
    lightSecondary = Red70,
)

private val LightExtendedColors = ExtendedColors(
    lightPrimary = Orange30,
    lightSecondary = Red30,
)

private val DarkColorScheme = darkColorScheme(
    primary = Orange60,
    secondary = Red60,
    tertiary = Orange80,

    background = Color(0xFF2D2C31),
    surface = Color(0xFF2D2C31),
    onPrimary = White50,
    onSecondary = White50,
    onTertiary = White50,
    onBackground = White50,
    onSurface = White50,
)

private val LightColorScheme = lightColorScheme(
    primary = Orange50,
    secondary = Red50,
    tertiary = Orange10,

    background = White50,
    surface = White50,
    onPrimary = White50,
    onSecondary = White50,
    onTertiary = White50,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
)

val LocalExtendedColors = staticCompositionLocalOf { LightExtendedColors }

@Composable
fun PocketdexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val extendedColors = if (darkTheme) DarkExtendedColors else LightExtendedColors

    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}