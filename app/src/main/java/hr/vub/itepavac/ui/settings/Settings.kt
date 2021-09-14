package hr.vub.itepavac.ui.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import hr.vub.itepavac.R
import hr.vub.itepavac.data.preferences.Language
import hr.vub.itepavac.data.preferences.Theme
import hr.vub.itepavac.ui.shared.EventXyzTopBar
import hr.vub.itepavac.ui.theme.EventXyzTheme

@Composable
fun Settings(onNavigateUp: () -> Unit) {
    Settings(
        viewModel = hiltViewModel(),
        onNavigateUp = onNavigateUp
    )
}

@Composable
private fun Settings(
    viewModel: SettingsViewModel,
    onNavigateUp: () -> Unit,
) {
    val userPreferences by viewModel.userPreferences.collectAsState()
    val dialogState by viewModel.dialogState.collectAsState()

    Scaffold(
        topBar = { EventXyzTopBar(titleResId = R.string.settings, onNavigateUp = onNavigateUp) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(vertical = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Divider()
            SettingItem(
                labelResId = R.string.settings_theme,
                valueResId = userPreferences.theme.labelResId,
                onClick = viewModel::onSelectTheme
            )
            Divider()
        }
    }

    SettingPickerDialog(
        viewModel = viewModel,
        dialogState = dialogState
    )
}

@Composable
private fun SettingItem(
    @StringRes labelResId: Int,
    @StringRes valueResId: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick.invoke() }
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(labelResId),
                style = EventXyzTheme.typography.subtitle1,
                color = EventXyzTheme.colors.textSubtitle
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = stringResource(valueResId),
                style = EventXyzTheme.typography.h6
            )
        }

        Icon(
            painter = EventXyzTheme.icons.chevronRight,
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun SettingPickerDialog(
    viewModel: SettingsViewModel,
    dialogState: SettingsDialogState
) {
    when (dialogState) {
        SettingsDialogState.Theme -> ThemePicker(
            onDismiss = viewModel::onPickerDismiss,
            onSelected = viewModel::onThemeSelected
        )
        SettingsDialogState.Language -> LanguagePicker(
            onDismiss = viewModel::onPickerDismiss,
            onSelected = viewModel::onLanguageSelected
        )
    }
}

@Composable
private fun ThemePicker(onDismiss: () -> Unit, onSelected: (Theme) -> Unit) {
    val items = remember { Theme.values().map { it.labelResId } }
    PickerDialog(
        items = items,
        onItemSelected = { item ->
            val theme = Theme.values().first { it.labelResId == item }
            onSelected.invoke(theme)
        },
        onDismissRequest = onDismiss
    )
}

@Composable
private fun LanguagePicker(onDismiss: () -> Unit, onSelected: (Language) -> Unit) {
    val items = remember { Language.values().map { it.labelResId } }
    PickerDialog(
        items = items,
        onItemSelected = { item ->
            val language = Language.values().first { it.labelResId == item }
            onSelected.invoke(language)
        },
        onDismissRequest = onDismiss
    )
}

@Composable
private fun PickerDialog(
    items: List<Int>,
    onItemSelected: (Int) -> Unit,
    onDismissRequest: () -> Unit = {}
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(shape = MaterialTheme.shapes.medium) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp)
            ) {
                Text(
                    text = stringResource(R.string.choose),
                    style = EventXyzTheme.typography.h5,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                items.forEach { item ->
                    Text(
                        text = stringResource(item),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onItemSelected.invoke(item) }
                            .padding(vertical = 12.dp, horizontal = 20.dp)
                    )
                }
            }
        }
    }
}
