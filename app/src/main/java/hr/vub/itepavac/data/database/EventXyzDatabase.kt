package hr.vub.itepavac.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import hr.vub.itepavac.data.database.dao.ArtistDao
import hr.vub.itepavac.data.database.dao.EventDao
import hr.vub.itepavac.data.database.entities.ArtistEntity
import hr.vub.itepavac.data.database.entities.EventEntity

@Database(
    entities = [
        ArtistEntity::class,
        EventEntity::class
    ],
    version = 1
)
abstract class EventXyzDatabase : RoomDatabase() {

    abstract fun artistDao(): ArtistDao
    abstract fun eventsDao(): EventDao
}
