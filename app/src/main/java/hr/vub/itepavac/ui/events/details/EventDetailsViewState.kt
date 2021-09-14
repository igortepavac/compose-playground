package hr.vub.itepavac.ui.events.details

import hr.vub.itepavac.domain.Event

sealed class EventDetailsViewState {

    object Loading : EventDetailsViewState()

    data class Data(val event: Event) : EventDetailsViewState()
}
