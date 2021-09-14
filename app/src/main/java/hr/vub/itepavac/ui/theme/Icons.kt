package hr.vub.itepavac.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import hr.vub.itepavac.R

object Icons {

    val calendar: Painter
        @Composable
        get() = painterResource(id = R.drawable.ic_calendar)

    val menu: Painter
        @Composable
        get() = painterResource(id = R.drawable.ic_menu)

    val arrowBack: Painter
        @Composable
        get() = painterResource(id = R.drawable.ic_arrow_back)

    val edit: Painter
        @Composable
        get() = painterResource(id = R.drawable.ic_edit)

    val delete: Painter
        @Composable
        get() = painterResource(id = R.drawable.ic_delete)

    val add: Painter
        @Composable
        get() = painterResource(id = R.drawable.ic_add)

    val chevronRight: Painter
        @Composable
        get() = painterResource(id = R.drawable.ic_chevron_right)
}
