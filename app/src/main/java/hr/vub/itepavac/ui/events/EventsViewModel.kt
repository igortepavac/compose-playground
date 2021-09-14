package hr.vub.itepavac.ui.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.vub.itepavac.data.repositories.EventsRepository
import hr.vub.itepavac.ui.shared.ExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val exceptionHandler: ExceptionHandler,
    private val eventsRepository: EventsRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow<EventsViewState>(EventsViewState.Loading)
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch(exceptionHandler) {
            eventsRepository.getAll().collectLatest { events ->
                _viewState.value = if (events.isEmpty()) {
                    EventsViewState.Empty
                } else {
                    EventsViewState.Data(events)
                }
            }
        }
    }
}
