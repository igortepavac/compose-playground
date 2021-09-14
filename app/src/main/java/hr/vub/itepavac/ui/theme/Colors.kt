package hr.vub.itepavac.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

data class Colors(
    val textSubtitle: Color,
    val background: Color,
    val surface: Color
)

val lightColors = with(ColorPalette) {
    Colors(
        textSubtitle = TextSubtitleLight,
        background = BackgroundLight,
        surface = SurfaceLight
    )
}

val darkColors = with(ColorPalette) {
    Colors(
        textSubtitle = TextSubtitleDark,
        background = BackgroundDark,
        surface = SurfaceDark
    )
}

val LocalEventXyzColors = compositionLocalOf { lightColors }
