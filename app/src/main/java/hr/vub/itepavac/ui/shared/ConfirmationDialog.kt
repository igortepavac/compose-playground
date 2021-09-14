package hr.vub.itepavac.ui.shared

import androidx.annotation.StringRes
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import hr.vub.itepavac.R

@Stable
class DialogState {

    var isVisible by mutableStateOf(false)

    companion object {
        val Saver: Saver<DialogState, *> = listSaver(
            save = {
                listOf(it.isVisible)
            },
            restore = {
                DialogState().apply {
                    isVisible = it[0]
                }
            }
        )
    }
}

@Composable
fun rememberDialogState() = rememberSaveable(saver = DialogState.Saver) {
    DialogState()
}

@Composable
fun ConfirmationDialog(
    state: DialogState,
    @StringRes titleResId: Int,
    @StringRes messageResId: Int,
    onActionConfirmed: () -> Unit,
    onActionRejected: () -> Unit = {},
    onDismissRequest: () -> Unit = { state.isVisible = false }
) {
    if (state.isVisible) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text(text = stringResource(titleResId)) },
            text = { Text(text = stringResource(messageResId)) },
            confirmButton = {
                TextButton(onClick = {
                    state.isVisible = false
                    onActionConfirmed.invoke()
                }) {
                    Text(text = stringResource(R.string.confirm))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    state.isVisible = false
                    onActionRejected.invoke()
                }) {
                    Text(text = stringResource(R.string.cancel))
                }
            }
        )
    }
}
