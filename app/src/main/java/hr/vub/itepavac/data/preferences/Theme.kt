package hr.vub.itepavac.data.preferences

import androidx.annotation.StringRes
import hr.vub.itepavac.R

enum class Theme(@StringRes val labelResId: Int) {
    LIGHT(R.string.theme_light),
    DARK(R.string.theme_dark),
    SYSTEM(R.string.theme_system);
}
