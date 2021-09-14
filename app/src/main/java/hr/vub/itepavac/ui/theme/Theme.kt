package hr.vub.itepavac.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.material.Colors as MaterialColors
import androidx.compose.material.Typography as MaterialTypography

@Composable
fun EventXyzTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = remember(darkTheme) { if (darkTheme) darkColors else lightColors }
    val materialColors = remember(darkTheme) { getMaterialColors(darkTheme) }

    val materialTypography = remember {
        MaterialTypography(
            h4 = EventXyzTheme.typography.h4,
            h6 = EventXyzTheme.typography.h6,
            body1 = EventXyzTheme.typography.body1
        )
    }

    MaterialTheme(
        colors = materialColors,
        typography = materialTypography,
        shapes = Shapes
    ) {
        CompositionLocalProvider(
            LocalEventXyzColors provides colors,
            content = content
        )
    }
}

fun getMaterialColors(darkTheme: Boolean): MaterialColors {
    return with(EventXyzTheme.colorPalette) {
        if (darkTheme) {
            darkColors(
                primary = AppPurple,
                primaryVariant = AppPurpleDark,
                background = BackgroundDark,
                surface = SurfaceDark
            )
        } else {
            lightColors(
                primary = AppPurpleMediumDark,
                primaryVariant = AppPurpleDark,
                background = BackgroundLight,
                surface = SurfaceLight
            )
        }
    }
}

object EventXyzTheme {

    val icons = Icons
    val typography = Typography
    val colorPalette = ColorPalette
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalEventXyzColors.current
}
