package hr.vub.itepavac.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hr.vub.itepavac.data.database.entities.ArtistEntity

@Dao
interface ArtistDao {

    @Query("SELECT * FROM artist")
    suspend fun getAll(): List<ArtistEntity>

    @Query("SELECT * FROM artist WHERE :id = id")
    suspend fun getById(id: String): ArtistEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg artists: ArtistEntity)
}
