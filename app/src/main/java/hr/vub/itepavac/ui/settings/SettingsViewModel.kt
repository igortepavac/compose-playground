package hr.vub.itepavac.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.vub.itepavac.data.preferences.EventXyzPreferences
import hr.vub.itepavac.data.preferences.Language
import hr.vub.itepavac.data.preferences.Theme
import hr.vub.itepavac.data.preferences.UserPreferences
import hr.vub.itepavac.ui.shared.ExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val exceptionHandler: ExceptionHandler,
    private val preferences: EventXyzPreferences,
) : ViewModel() {

    private val _userPreferences = MutableStateFlow(UserPreferences.empty())
    val userPreferences = _userPreferences.asStateFlow()

    private val _dialogState = MutableStateFlow<SettingsDialogState>(SettingsDialogState.None)
    val dialogState = _dialogState.asStateFlow()

    init {
        viewModelScope.launch(exceptionHandler) {
            preferences.userPreferencesFlow()
                .collectLatest { preferences ->
                    _userPreferences.value = preferences
                }
        }
    }

    fun onSelectTheme() {
        _dialogState.value = SettingsDialogState.Theme
    }

    fun onSelectLanguage() {
        _dialogState.value = SettingsDialogState.Language
    }

    fun onThemeSelected(theme: Theme) {
        viewModelScope.launch(exceptionHandler) {
            preferences.setTheme(theme)
        }
        _userPreferences.value = userPreferences.value.copy(theme = theme)
        _dialogState.value = SettingsDialogState.None
    }

    fun onLanguageSelected(language: Language) {
        viewModelScope.launch(exceptionHandler) {
            preferences.setLanguage(language)
        }
        _userPreferences.value = userPreferences.value.copy(language = language)
        _dialogState.value = SettingsDialogState.None
    }

    fun onPickerDismiss() {
        _dialogState.value = SettingsDialogState.None
    }
}
