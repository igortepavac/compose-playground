package hr.vub.itepavac.ui.settings

sealed class SettingsDialogState {

    object None : SettingsDialogState()
    object Theme : SettingsDialogState()
    object Language : SettingsDialogState()
}
