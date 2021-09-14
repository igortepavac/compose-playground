package hr.vub.itepavac.data.mappers

import hr.vub.itepavac.data.database.entities.ArtistEntity
import hr.vub.itepavac.domain.Artist
import javax.inject.Inject

class ArtistMapper @Inject constructor() {

    fun toDomain(entity: ArtistEntity): Artist {
        return with(entity) {
            Artist(
                id = id,
                name = name,
                imageUrl = imageUrl
            )
        }
    }

    fun toEntity(domain: Artist): ArtistEntity {
        return with(domain) {
            ArtistEntity(
                id = id,
                name = name,
                imageUrl = imageUrl
            )
        }
    }
}
