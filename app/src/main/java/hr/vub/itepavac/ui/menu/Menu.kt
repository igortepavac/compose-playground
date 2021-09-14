package hr.vub.itepavac.ui.menu

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hr.vub.itepavac.R
import hr.vub.itepavac.ui.theme.EventXyzTheme

@Composable
fun Menu(
    onNavigateToArtistsOverview: () -> Unit,
    onNavigateToNewEvent: () -> Unit,
    onNavigateToSettings: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 32.dp)
    ) {
        Text(
            text = stringResource(R.string.menu),
            modifier = Modifier.padding(horizontal = 20.dp),
            style = EventXyzTheme.typography.h3
        )
        Spacer(modifier = Modifier.height(32.dp))
        Divider()
        MenuItem(labelResId = R.string.artists_overview, onClick = onNavigateToArtistsOverview)
        Divider()
        MenuItem(labelResId = R.string.add_new_event, onClick = onNavigateToNewEvent)
        Divider()
        MenuItem(labelResId = R.string.settings, onClick = onNavigateToSettings)
        Divider()
    }
}

@Composable
private fun MenuItem(
    @StringRes labelResId: Int,
    onClick: () -> Unit
) {
    Text(
        text = stringResource(labelResId),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick.invoke() }
            .padding(horizontal = 20.dp, vertical = 16.dp),
        style = EventXyzTheme.typography.h5
    )
}
