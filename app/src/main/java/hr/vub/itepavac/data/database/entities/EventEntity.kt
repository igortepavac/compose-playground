package hr.vub.itepavac.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
data class EventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val description: String,
    val location: String,
    val date: String,
    val artistId: String
)
