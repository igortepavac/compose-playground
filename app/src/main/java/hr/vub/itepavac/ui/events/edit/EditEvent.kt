package hr.vub.itepavac.ui.events.edit

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.vub.itepavac.R
import hr.vub.itepavac.ui.shared.ArtistCard
import hr.vub.itepavac.ui.shared.EventXyzTopBar

@Composable
fun EditEvent(
    onNavigateUp: () -> Unit,
    onSelectArtist: () -> Unit,
) {
    EditEvent(
        viewModel = hiltViewModel(),
        onNavigateUp = onNavigateUp,
        onSelectArtist = onSelectArtist
    )
}

@Composable
private fun EditEvent(
    viewModel: EditEventViewModel,
    onNavigateUp: () -> Unit,
    onSelectArtist: () -> Unit
) {
    val screenTitle by viewModel.screenTitle.collectAsState()
    val selectedArtist by viewModel.selectedArtist.collectAsState()
    val eventDetailsData by viewModel.eventDetailsData.collectAsState()
    val eventDetailsErrorState by viewModel.eventDetailsErrorState.collectAsState()

    val eventSaved by viewModel.eventSavedState.collectAsState()
    if (eventSaved) {
        onNavigateUp.invoke()
    }

    val errorMessageRes by viewModel.errorMessageState.collectAsState()
    val errorMessage = errorMessageRes?.let { stringResource(id = it) }
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            scaffoldState.snackbarHostState.showSnackbar(errorMessage)
            viewModel.clearErrorMessage()
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            EventXyzTopBar(
                titleResId = screenTitle,
                onNavigateUp = onNavigateUp
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(vertical = 32.dp, horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ArtistSelection(
                artistName = selectedArtist?.name,
                artistImageUrl = selectedArtist?.imageUrl,
                isError = eventDetailsErrorState.artistIsError,
                onSelectArtist = onSelectArtist
            )
            Spacer(modifier = Modifier.height(24.dp))
            EventDetailsForm(
                eventDetailsData = eventDetailsData,
                eventDetailsErrorState = eventDetailsErrorState,
                onDataChange = { viewModel.onDataChanged(it) }
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = viewModel::onSave,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(text = stringResource(R.string.save))
            }
        }
    }
}

@Composable
fun ArtistSelection(
    artistName: String?,
    artistImageUrl: String?,
    isError: Boolean,
    onSelectArtist: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (isError) {
                    Modifier.border(2.dp, Color.Red)
                } else {
                    Modifier
                }
            )
    ) {
        ArtistCard(
            artistName = artistName ?: stringResource(R.string.pick_artist),
            artistImageUrl = artistImageUrl.orEmpty(),
            onClick = onSelectArtist
        )
    }
}

@Composable
private fun EventDetailsForm(
    eventDetailsData: EventDetailsData,
    eventDetailsErrorState: EventDetailsErrorState,
    onDataChange: (EventDetailsData) -> Unit
) {
    DataField(
        labelResId = R.string.date,
        value = eventDetailsData.date,
        isError = eventDetailsErrorState.dateIsError,
        onValueChange = { onDataChange.invoke(eventDetailsData.copy(date = it)) }
    )
    Spacer(modifier = Modifier.height(10.dp))
    DataField(
        labelResId = R.string.location,
        value = eventDetailsData.location,
        isError = eventDetailsErrorState.locationIsError,
        onValueChange = { onDataChange.invoke(eventDetailsData.copy(location = it)) }
    )
    Spacer(modifier = Modifier.height(10.dp))
    DataField(
        labelResId = R.string.description,
        value = eventDetailsData.description,
        isError = eventDetailsErrorState.descriptionIsError,
        onValueChange = { onDataChange.invoke(eventDetailsData.copy(description = it)) }
    )
}

@Composable
private fun DataField(
    @StringRes labelResId: Int,
    value: String,
    isError: Boolean,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(stringResource(labelResId)) },
        isError = isError
    )
}
