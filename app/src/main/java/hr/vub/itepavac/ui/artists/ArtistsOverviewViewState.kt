package hr.vub.itepavac.ui.artists

import hr.vub.itepavac.domain.Artist

sealed class ArtistsOverviewViewState {

    object Loading : ArtistsOverviewViewState()

    data class Data(val artists: List<Artist>) : ArtistsOverviewViewState()
}
