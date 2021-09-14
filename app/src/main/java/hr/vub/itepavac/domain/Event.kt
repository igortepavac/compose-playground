package hr.vub.itepavac.domain

data class Event(
    val id: Int = 0,
    val artist: Artist,
    val description: String,
    val location: String,
    val date: String
)
