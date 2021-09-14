package hr.vub.itepavac.ui.events.details

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import hr.vub.itepavac.R
import hr.vub.itepavac.domain.Event
import hr.vub.itepavac.ui.shared.ConfirmationDialog
import hr.vub.itepavac.ui.shared.EventXyzTopBar
import hr.vub.itepavac.ui.shared.ProgressIndicatorWrapper
import hr.vub.itepavac.ui.shared.rememberDialogState
import hr.vub.itepavac.ui.theme.EventXyzTheme

@Composable
fun EventDetails(
    onNavigateUp: () -> Unit,
    onEditEvent: () -> Unit
) {
    EventDetails(
        viewModel = hiltViewModel(),
        onNavigateUp = onNavigateUp,
        onEditEvent = onEditEvent
    )
}

@Composable
private fun EventDetails(
    viewModel: EventDetailsViewModel,
    onNavigateUp: () -> Unit,
    onEditEvent: () -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()
    val confirmationDialogState = rememberDialogState()
    Scaffold(
        topBar = {
            TopBar(
                onNavigateUp = onNavigateUp,
                onEdit = onEditEvent,
                onDelete = {
                    confirmationDialogState.isVisible = true
                }
            )
        }
    ) { paddingValues ->
        viewState.let { state ->
            when (state) {
                is EventDetailsViewState.Loading -> ProgressIndicatorWrapper()
                is EventDetailsViewState.Data -> EventDetailsContent(state.event)
            }
        }
    }
    ConfirmationDialog(
        state = confirmationDialogState,
        titleResId = R.string.caution,
        messageResId = R.string.delete_event_confirmation_message,
        onActionConfirmed = {
            viewModel.onDelete()
            onNavigateUp.invoke()
        }
    )
}

@Composable
private fun TopBar(
    onNavigateUp: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    EventXyzTopBar(
        titleResId = R.string.details,
        onNavigateUp = onNavigateUp,
        actions = {
            IconButton(onClick = onEdit) {
                Icon(
                    painter = EventXyzTheme.icons.edit,
                    contentDescription = stringResource(R.string.edit)
                )
            }
            IconButton(onClick = onDelete) {
                Icon(
                    painter = EventXyzTheme.icons.delete,
                    contentDescription = stringResource(R.string.delete)
                )
            }
        }
    )
}

@Composable
private fun EventDetailsContent(
    event: Event
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ArtistDetails(artistName = event.artist.name, artistImageUrl = event.artist.imageUrl)
        Spacer(modifier = Modifier.height(32.dp))
        DataField(R.string.date, event.date)
        Spacer(modifier = Modifier.height(16.dp))
        DataField(R.string.location, event.location)
        Spacer(modifier = Modifier.height(16.dp))
        DataField(R.string.description, event.description)
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun ArtistDetails(
    artistName: String,
    artistImageUrl: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberImagePainter(artistImageUrl),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(250.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = artistName,
            textAlign = TextAlign.Center,
            style = EventXyzTheme.typography.h4,
        )
    }
}

@Composable
private fun DataField(
    @StringRes labelResId: Int,
    value: String
) {
    Column {
        Text(
            text = stringResource(labelResId),
            style = EventXyzTheme.typography.subtitle1
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value)
    }
}

