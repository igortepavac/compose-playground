package hr.vub.itepavac.data.mappers

import hr.vub.itepavac.data.database.entities.EventEntity
import hr.vub.itepavac.domain.Artist
import hr.vub.itepavac.domain.Event
import javax.inject.Inject

class EventMapper @Inject constructor() {

    fun toDomain(entity: EventEntity, artist: Artist): Event {
        return with(entity) {
            Event(
                id = id,
                artist = artist,
                description = description,
                location = location,
                date = date
            )
        }
    }

    fun toEntity(domain: Event): EventEntity {
        return with(domain) {
            EventEntity(
                id = id,
                description = description,
                location = location,
                date = date,
                artistId = artist.id,
            )
        }
    }
}
