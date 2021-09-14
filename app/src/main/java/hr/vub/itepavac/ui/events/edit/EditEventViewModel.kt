package hr.vub.itepavac.ui.events.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.vub.itepavac.R
import hr.vub.itepavac.data.repositories.EventsRepository
import hr.vub.itepavac.domain.Artist
import hr.vub.itepavac.domain.Event
import hr.vub.itepavac.ui.shared.ArtistSelectionHandler
import hr.vub.itepavac.ui.shared.ExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditEventViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val exceptionHandler: ExceptionHandler,
    private val eventsRepository: EventsRepository,
    private val artistSelectionHandler: ArtistSelectionHandler
) : ViewModel() {

    private var eventId: Int? = savedStateHandle.get("eventId")

    val screenTitle = MutableStateFlow(
        if (eventId != null) R.string.edit_event else R.string.add_event
    ).asStateFlow()

    private val _selectedArtist = MutableStateFlow<Artist?>(null)
    val selectedArtist = _selectedArtist.asStateFlow()

    private val _eventDetailsData = MutableStateFlow(EventDetailsData())
    val eventDetailsData = _eventDetailsData.asStateFlow()

    private val _eventDetailsErrorState = MutableStateFlow(EventDetailsErrorState())
    val eventDetailsErrorState = _eventDetailsErrorState.asStateFlow()

    private val _errorMessageState = MutableStateFlow<Int?>(null)
    val errorMessageState = _errorMessageState.asStateFlow()

    private val _eventSavedState = MutableStateFlow(false)
    val eventSavedState = _eventSavedState.asStateFlow()

    init {
        initArtistSelectionCollector()
        initEventDetailsData()
    }

    private fun initEventDetailsData() {
        if (eventId != null) {
            viewModelScope.launch(exceptionHandler) {
                val event = eventsRepository.get(eventId!!)
                _selectedArtist.value = event.artist
                _eventDetailsData.value = EventDetailsData(
                    date = event.date,
                    location = event.location,
                    description = event.description
                )
            }
        }
    }

    private fun initArtistSelectionCollector() {
        viewModelScope.launch(exceptionHandler) {
            artistSelectionHandler.selected.collect { artist ->
                artist?.let {
                    _selectedArtist.value = it
                    artistSelectionHandler.clear()
                    clearErrorState()
                }
            }
        }
    }

    fun onDataChanged(data: EventDetailsData) {
        _eventDetailsData.value = data
        clearErrorState()
    }

    private fun clearErrorState() {
        _eventDetailsErrorState.value = EventDetailsErrorState()
    }

    fun onSave() {
        val dataState = eventDetailsData.value
        val errorState = EventDetailsErrorState(
            artistIsError = selectedArtist.value == null,
            dateIsError = dataState.date.isEmpty(),
            locationIsError = dataState.location.isEmpty(),
            descriptionIsError = dataState.description.isEmpty(),
        )
        if (errorState.hasAnyError()) {
            _eventDetailsErrorState.value = errorState
            _errorMessageState.value = R.string.event_edit_error
        } else {
            saveEvent(dataState)
        }
    }

    private fun saveEvent(dataState: EventDetailsData) {
        viewModelScope.launch(exceptionHandler) {
            val event = Event(
                id = eventId ?: 0, // If the ID is 0, Room will create a new entry.
                artist = selectedArtist.value!!,
                date = dataState.date,
                location = dataState.location,
                description = dataState.description
            )
            eventsRepository.save(event)
            _eventSavedState.value = true
        }
    }

    fun clearErrorMessage() {
        _errorMessageState.value = null
    }
}
