package hr.vub.itepavac.data.repositories

import hr.vub.itepavac.data.database.dao.EventDao
import hr.vub.itepavac.data.mappers.EventMapper
import hr.vub.itepavac.domain.Event
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class EventsRepository @Inject constructor(
    private val eventDao: EventDao,
    private val eventMapper: EventMapper,
    private val artistsRepository: ArtistsRepository
) {

    fun getAll(): Flow<List<Event>> = eventDao.getAll().map {
        it.map { eventEntity ->
            val artist = artistsRepository.getById(eventEntity.artistId)
            eventMapper.toDomain(eventEntity, artist)
        }
    }

    suspend fun get(id: Int): Event {
        val entity = eventDao.get(id)
        val artist = artistsRepository.getById(entity.artistId)
        return eventMapper.toDomain(entity, artist)
    }

    suspend fun save(event: Event) {
        eventDao.insertAll(eventMapper.toEntity(event))
    }

    suspend fun insertAll(events: List<Event>) {
        val entities = events.map { eventMapper.toEntity(it) }.toTypedArray()
        eventDao.insertAll(*entities)
    }

    suspend fun delete(event: Event) {
        eventDao.delete(eventMapper.toEntity(event))
    }
}
