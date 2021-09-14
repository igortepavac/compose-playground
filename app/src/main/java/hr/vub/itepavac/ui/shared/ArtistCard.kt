package hr.vub.itepavac.ui.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import hr.vub.itepavac.ui.theme.EventXyzTheme

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ArtistCard(
    artistName: String,
    artistImageUrl: String,
    onClick: (() -> Unit)? = null
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier.then(
            if (onClick != null) {
                Modifier.clickable { onClick.invoke() }
            } else {
                Modifier
            }
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ArtistImage(artistImageUrl)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = artistName,
                style = EventXyzTheme.typography.h5,
            )
        }
    }
}

@Composable
fun RowScope.ArtistImage(artistImageUrl: String) {
    Image(
        painter = rememberImagePainter(artistImageUrl),
        contentDescription = null,
        modifier = Modifier
            .align(Alignment.CenterVertically)
            .size(72.dp)
            .clip(RoundedCornerShape(4.dp))
    )
}
