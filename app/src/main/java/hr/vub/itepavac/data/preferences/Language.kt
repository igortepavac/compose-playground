package hr.vub.itepavac.data.preferences

import androidx.annotation.StringRes
import hr.vub.itepavac.R
import java.util.Locale

enum class Language(@StringRes val labelResId: Int, val locale: Locale) {
    ENGLISH(R.string.language_english, Locale("en")),
    CROATIAN(R.string.language_croatian, Locale("hr"))
}
