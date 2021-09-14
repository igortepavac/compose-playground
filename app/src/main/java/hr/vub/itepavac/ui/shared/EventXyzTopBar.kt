package hr.vub.itepavac.ui.shared

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import hr.vub.itepavac.R
import hr.vub.itepavac.ui.theme.EventXyzTheme

@Composable
fun EventXyzTopBar(
    @StringRes titleResId: Int,
    onNavigateUp: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = { Text(text = stringResource(titleResId)) },
        backgroundColor = MaterialTheme.colors.background,
        navigationIcon = {
            IconButton(onClick = onNavigateUp) {
                Icon(
                    painter = EventXyzTheme.icons.arrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        },
        actions = actions
    )
}
