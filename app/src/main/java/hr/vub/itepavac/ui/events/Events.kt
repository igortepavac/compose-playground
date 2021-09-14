package hr.vub.itepavac.ui.events

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import hr.vub.itepavac.R
import hr.vub.itepavac.domain.Event
import hr.vub.itepavac.ui.shared.ArtistImage
import hr.vub.itepavac.ui.shared.ProgressIndicatorWrapper
import hr.vub.itepavac.ui.theme.EventXyzTheme

@Composable
fun Events(
    onNavigateToDetails: (Int) -> Unit,
    onAddNewEvent: () -> Unit
) {
    Events(
        viewModel = hiltViewModel(),
        onNavigateToDetails = onNavigateToDetails,
        onAddNewEvent = onAddNewEvent
    )
}

@Composable
private fun Events(
    viewModel: EventsViewModel,
    onNavigateToDetails: (Int) -> Unit,
    onAddNewEvent: () -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()
    Scaffold(
        floatingActionButton = { AddEventFab(onClick = onAddNewEvent) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            viewState.let { state ->
                when (state) {
                    is EventsViewState.Loading -> ProgressIndicatorWrapper()
                    is EventsViewState.Empty -> EmptyState()
                    is EventsViewState.Data -> EventList(state.events, onNavigateToDetails)
                }
            }
        }
    }
}

@Composable
private fun AddEventFab(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier.padding(16.dp),
        backgroundColor = EventXyzTheme.colorPalette.AppPurple
    ) {
        Icon(
            painter = EventXyzTheme.icons.add,
            contentDescription = stringResource(R.string.add_new_event)
        )
    }
}

@Composable
private fun EventList(
    events: List<Event>,
    onNavigateToDetails: (Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            EventsHeader()
        }
        items(events) {
            EventItem(it, onEventSelect = { onNavigateToDetails(it.id) })
        }
    }
}

@Composable
private fun EventsHeader() {
    Text(
        text = stringResource(id = R.string.events_for_you),
        style = EventXyzTheme.typography.h3
    )
    Spacer(modifier = Modifier.height(32.dp))
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun EventItem(
    event: Event,
    onEventSelect: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier.clickable { onEventSelect.invoke() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ArtistImage(event.artist.imageUrl)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = event.artist.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = EventXyzTheme.typography.h6
                )
                Spacer(modifier = Modifier.padding(bottom = 4.dp))
                Text(
                    text = event.location,
                    color = EventXyzTheme.colors.textSubtitle,
                    style = EventXyzTheme.typography.subtitle2,
                )
                Text(
                    text = event.date,
                    color = EventXyzTheme.colors.textSubtitle,
                    style = EventXyzTheme.typography.subtitle2,
                )
            }

        }
    }
}

@Composable
fun EmptyState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.events_empty_placeholder),
            textAlign = TextAlign.Center,
            style = EventXyzTheme.typography.h5,
        )
    }
}
