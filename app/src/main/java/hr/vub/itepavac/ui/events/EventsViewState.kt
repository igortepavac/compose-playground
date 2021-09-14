package hr.vub.itepavac.ui.events

import hr.vub.itepavac.domain.Event

sealed class EventsViewState {

    object Loading : EventsViewState()
    object Empty : EventsViewState()

    data class Data(val events: List<Event>) : EventsViewState()
}
