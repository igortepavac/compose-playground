package hr.vub.itepavac.ui.events.edit

data class EventDetailsData(
    val date: String = "",
    val location: String = "",
    val description: String = "",
)

data class EventDetailsErrorState(
    val artistIsError: Boolean = false,
    val dateIsError: Boolean = false,
    val locationIsError: Boolean = false,
    val descriptionIsError: Boolean = false,
) {

    fun hasAnyError(): Boolean = artistIsError || dateIsError || locationIsError || descriptionIsError
}
