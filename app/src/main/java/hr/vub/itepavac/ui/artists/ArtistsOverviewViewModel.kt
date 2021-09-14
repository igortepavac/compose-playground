package hr.vub.itepavac.ui.artists

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.vub.itepavac.data.repositories.ArtistsRepository
import hr.vub.itepavac.domain.Artist
import hr.vub.itepavac.ui.shared.ArtistSelectionHandler
import hr.vub.itepavac.ui.shared.ExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistsOverviewViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val exceptionHandler: ExceptionHandler,
    private val artistsRepository: ArtistsRepository,
    private val artistSelectionHandler: ArtistSelectionHandler
) : ViewModel() {

    private val _selectionMode = MutableStateFlow(savedStateHandle.get<Boolean>("select") ?: false)
    val selectionMode = _selectionMode.asStateFlow()

    private val _viewState = MutableStateFlow<ArtistsOverviewViewState>(ArtistsOverviewViewState.Loading)
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch(exceptionHandler) {
            val artists = artistsRepository.getAll()
            _viewState.value = ArtistsOverviewViewState.Data(artists)
        }
    }

    fun onSelect(artist: Artist) {
        artistSelectionHandler.select(artist)
    }
}
