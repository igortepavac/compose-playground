package hr.vub.itepavac.ui.shared

import hr.vub.itepavac.domain.Artist
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArtistSelectionHandler @Inject constructor() {

    private val _selected = MutableStateFlow<Artist?>(null)
    val selected = _selected.asStateFlow()

    fun select(artist: Artist?) {
        _selected.value = artist
    }

    fun clear() {
        _selected.value = null
    }
}
