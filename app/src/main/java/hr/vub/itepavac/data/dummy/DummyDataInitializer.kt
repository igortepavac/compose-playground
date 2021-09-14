package hr.vub.itepavac.data.dummy

import hr.vub.itepavac.data.repositories.ArtistsRepository
import hr.vub.itepavac.data.repositories.EventsRepository
import hr.vub.itepavac.domain.Artist
import hr.vub.itepavac.domain.Event
import javax.inject.Inject

class DummyDataInitializer @Inject constructor(
    private val artistsRepository: ArtistsRepository,
    private val eventsRepository: EventsRepository,
) {

    suspend fun init() {
        initArtists()
        initEvents()
    }

    private suspend fun initArtists() {
        val artists = listOf(
            Artist(
                id = "0oSGxfWSnnOXhD2fKuz2Gy", // Spotify ID
                name = "David Bowie",
                imageUrl = "https://i.scdn.co/image/ab6761610000e5eb0db3b11972a84207f256769b"
            ),
            Artist(
                id = "3dBVyJ7JuOMt4GE9607Qin",
                name = "T. Rex",
                imageUrl = "https://i.scdn.co/image/ab6761610000e5eb9d111de957311f838d3f6b6a"
            ),
            Artist(
                id = "0dRiUTGvNV17AMIULRYsvn",
                name = "Mr. Fingers",
                imageUrl = "https://i.scdn.co/image/1fdee30e100a8c59c18d04ea5262855cdfe55c72"
            ),
            Artist(
                id = "3qwabfaWewpfli7hMNM3O8",
                name = "Róisín Murphy",
                imageUrl = "https://i.scdn.co/image/ab6761610000e5eb8e3c92d71e875dab6067e1de"
            ),
            Artist(
                id = "1Zq8pfBl4ejCMrWdeAdphc",
                name = "Eddie Murphy",
                imageUrl = "https://i.scdn.co/image/140594b4b0839af5f01bbf6e5c19386b2f75a7c8"
            ),
            Artist(
                id = "3fMbdgg4jU18AjLCKBhRSm",
                name = "Michael Jackson",
                imageUrl = "https://i.scdn.co/image/ab6761610000e5eba2a0b9e3448c1e702de9dc90"
            ),
            Artist(
                id = "6o7PhWSdIRDWXevilpKRlK",
                name = "Ekatarina Velika",
                imageUrl = "https://i.scdn.co/image/ab6761610000e5eb8feb42cfc7a33c57066a278f"
            ),
            Artist(
                id = "0Wme57aoD5E4RPxcRP7MAM",
                name = "z++",
                imageUrl = "https://i.scdn.co/image/ab6761610000e5eb649fb716a0538048845cf333"
            ),
            Artist(
                id = "3m97PzF5kvkWOHMyZtRvUO",
                name = "Sajeta",
                imageUrl = "https://i.scdn.co/image/ab67616d0000b273e6f0865865c60cccbe5e161e"
            ),
            Artist(
                id = "0jhlqMZUO16o7MwU4VsUol",
                name = "nipplepeople",
                imageUrl = "https://i.scdn.co/image/ab67616d0000b27374c692dfb9f56dd2402ce81b"
            ),
            Artist(
                id = "7cuY76kWXDAACkri6Jp8ze",
                name = "Valentino Boskovic",
                imageUrl = "https://i.scdn.co/image/ab6761610000e5eb75956fda979fcab832d914cd"
            ),
        )
        artistsRepository.insertAll(artists)
    }

    private suspend fun initEvents() {
        val events = listOf(
            Event(
                id = 0,
                artist = Artist(
                    id = "7cuY76kWXDAACkri6Jp8ze",
                    name = "Valentino Boskovic",
                    imageUrl = "https://i.scdn.co/image/ab6761610000e5eb75956fda979fcab832d914cd"
                ),
                description = DEFAULT_DESCRIPTION,
                location = "Zagreb, Croatia",
                date = "20:00, 25.09.2021."
            ),
            Event(
                id = 0,
                artist = Artist(
                    id = "6o7PhWSdIRDWXevilpKRlK",
                    name = "Ekatarina Velika",
                    imageUrl = "https://i.scdn.co/image/ab6761610000e5eb8feb42cfc7a33c57066a278f"
                ),
                description = DEFAULT_DESCRIPTION,
                location = "Bjelovar, Croatia",
                date = "21:00, 18.09.2021."
            ),
            Event(
                id = 0,
                artist = Artist(
                    id = "0oSGxfWSnnOXhD2fKuz2Gy",
                    name = "David Bowie",
                    imageUrl = "https://i.scdn.co/image/ab6761610000e5eb0db3b11972a84207f256769b"
                ),
                description = DEFAULT_DESCRIPTION,
                location = "Zagreb, Croatia",
                date = "20:30, 20.10.2021."
            ),
            Event(
                id = 0,
                artist = Artist(
                    id = "3qwabfaWewpfli7hMNM3O8",
                    name = "Róisín Murphy",
                    imageUrl = "https://i.scdn.co/image/ab6761610000e5eb8e3c92d71e875dab6067e1de"
                ),
                description = DEFAULT_DESCRIPTION,
                location = "Zagreb, Croatia",
                date = "20:30, 20.10.2021."
            ),
            Event(
                id = 0,
                artist = Artist(
                    id = "7cuY76kWXDAACkri6Jp8ze",
                    name = "Valentino Boskovic",
                    imageUrl = "https://i.scdn.co/image/ab6761610000e5eb75956fda979fcab832d914cd"
                ),
                description = DEFAULT_DESCRIPTION,
                location = "Bjelovar, Croatia",
                date = "20:00, 24.09.2021."
            ),
            Event(
                id = 0,
                artist = Artist(
                    id = "6o7PhWSdIRDWXevilpKRlK",
                    name = "Ekatarina Velika",
                    imageUrl = "https://i.scdn.co/image/ab6761610000e5eb8feb42cfc7a33c57066a278f"
                ),
                description = DEFAULT_DESCRIPTION,
                location = "Zagreb, Croatia",
                date = "20:00, 25.09.2021."
            ),
            Event(
                id = 0,
                artist = Artist(
                    id = "3fMbdgg4jU18AjLCKBhRSm",
                    name = "Michael Jackson",
                    imageUrl = "https://i.scdn.co/image/ab6761610000e5eba2a0b9e3448c1e702de9dc90"
                ),
                description = DEFAULT_DESCRIPTION,
                location = "Zagreb, Croatia",
                date = "16:30, 14.12.2021."
            ),
            Event(
                id = 0,
                artist = Artist(
                    id = "3qwabfaWewpfli7hMNM3O8",
                    name = "Róisín Murphy",
                    imageUrl = "https://i.scdn.co/image/ab6761610000e5eb8e3c92d71e875dab6067e1de"
                ),
                description = DEFAULT_DESCRIPTION,
                location = "Zagreb, Croatia",
                date = "20:30, 20.10.2021."
            ),
        )
        eventsRepository.insertAll(events)
    }
}

private const val DEFAULT_DESCRIPTION =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur in aliquam arcu.\n\nDonec tristique turpis ut tempor bibendum. Nunc sit amet ornare quam. Etiam nec tellus luctus orci consequat euismod. Vestibulum cursus quis lorem vitae mollis. Curabitur ut massa nec ligula porttitor fermentum. Quisque sed vestibulum erat, vel fringilla metus. Aliquam eu aliquam enim, vel cursus augue. Duis mattis consequat ante vel tristique.\n\nOrci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Integer non enim quis justo congue scelerisque sit amet in est."
