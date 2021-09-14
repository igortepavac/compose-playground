package hr.vub.itepavac.data.repositories

import hr.vub.itepavac.data.database.dao.ArtistDao
import hr.vub.itepavac.data.mappers.ArtistMapper
import hr.vub.itepavac.domain.Artist
import javax.inject.Inject

class ArtistsRepository @Inject constructor(
    private val artistDao: ArtistDao,
    private val artistMapper: ArtistMapper
) {

    suspend fun getAll(): List<Artist> {
        return artistDao.getAll().map { artistMapper.toDomain(it) }
    }

    suspend fun getById(id: String): Artist {
        val entity = artistDao.getById(id)
        return artistMapper.toDomain(entity)
    }

    suspend fun insertAll(artists: List<Artist>) {
        val entities = artists.map { artistMapper.toEntity(it) }.toTypedArray()
        artistDao.insertAll(*entities)
    }
}
