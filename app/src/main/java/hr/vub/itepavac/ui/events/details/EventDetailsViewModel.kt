package hr.vub.itepavac.ui.events.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.vub.itepavac.data.repositories.EventsRepository
import hr.vub.itepavac.domain.Event
import hr.vub.itepavac.ui.shared.ExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val exceptionHandler: ExceptionHandler,
    private val eventsRepository: EventsRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow<EventDetailsViewState>(EventDetailsViewState.Loading)
    val viewState = _viewState.asStateFlow()

    private var event: Event? = null

    init {
        viewModelScope.launch(exceptionHandler) {
            val eventId = requireNotNull(savedStateHandle.get<Int>("eventId"))
            event = eventsRepository.get(eventId)
            _viewState.value = EventDetailsViewState.Data(event!!)
        }
    }

    fun onDelete() {
        event?.let {
            viewModelScope.launch(exceptionHandler) {
                eventsRepository.delete(it)
            }
        }
    }
}
