package hr.vub.itepavac.ui.artists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.vub.itepavac.R
import hr.vub.itepavac.domain.Artist
import hr.vub.itepavac.ui.shared.ArtistCard
import hr.vub.itepavac.ui.shared.EventXyzTopBar
import hr.vub.itepavac.ui.shared.ProgressIndicatorWrapper

@Composable
fun ArtistsOverview(
    onNavigateUp: () -> Unit,
) {
    ArtistsOverview(
        viewModel = hiltViewModel(),
        onNavigateUp = onNavigateUp
    )
}

@Composable
private fun ArtistsOverview(
    viewModel: ArtistsOverviewViewModel,
    onNavigateUp: () -> Unit,
) {
    val viewState by viewModel.viewState.collectAsState()
    val selectionMode by viewModel.selectionMode.collectAsState()
    val clickListener = remember(selectionMode) {
        if (selectionMode) {
            { artist: Artist ->
                viewModel.onSelect(artist)
                onNavigateUp.invoke()
            }
        } else {
            null
        }
    }
    Scaffold(
        topBar = {
            EventXyzTopBar(
                titleResId = R.string.artists,
                onNavigateUp = onNavigateUp
            )
        }
    ) {
        viewState.let { state ->
            when (state) {
                is ArtistsOverviewViewState.Loading -> ProgressIndicatorWrapper()
                is ArtistsOverviewViewState.Data -> ArtistList(artists = state.artists, onClick = clickListener)
            }
        }
    }
}

@Composable
private fun ArtistList(
    artists: List<Artist>,
    onClick: ((Artist) -> Unit)? = null
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(artists) { artist ->
            val onClickListener = remember(onClick) { onClick?.let { { onClick.invoke(artist) } } }
            ArtistCard(artistName = artist.name, artistImageUrl = artist.imageUrl, onClick = onClickListener)
        }
    }
}
