package hr.vub.itepavac.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.vub.itepavac.data.dummy.DummyDataInitializer
import hr.vub.itepavac.data.preferences.EventXyzPreferences
import hr.vub.itepavac.ui.shared.ExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exceptionHandler: ExceptionHandler,
    private val preferences: EventXyzPreferences,
    private val dummyDataInitializer: DummyDataInitializer
) : ViewModel() {
    private val _viewState = MutableStateFlow<MainViewState>(MainViewState.Loading)
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch(exceptionHandler) {
            preferences.userPreferencesFlow()
                .collect { userPreferences ->
                    if (!userPreferences.didInitializeDatabase) {
                        dummyDataInitializer.init()
                        preferences.setDidInitializeDatabase(true)
                    }
                    _viewState.value = MainViewState.Ready
                }
        }
    }
}
