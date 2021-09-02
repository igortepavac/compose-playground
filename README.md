## Music Event Manager

Aplikacija za vođenje i evidenciju javnih glazbenih događaja.

### Funkcionalnosti po ekranima:

1. Početni zaslon (splash activity)
2. Postavke
    - Promjena jezika aplikacije
    - Promjena teme aplikacije
3. Pretraživanje izvođača
4. Pregled događaja
5. Unos i uređivanje događaja

### Opis

Aplikacija se sastoji od 2 glavna entiteta: **_izvođač_** i **_događaj_**.

Korisnik ima mogućnost izvođenja CRUD operacija nad entitetom _događaj_. Jedan ekran se koristi za pregled i brisanje događaja, a drugi za unos i uređivanje pojedinog događaja.

Korisnik može pretraživati entitete izvođača. Popis izvođača dolazi iz predefinirane lokalne baze podataka ili sa jednog od javnih web servisa (npr. Discogs ili Spotify). // TODO: Define which one.

Također postoje postavke aplikacije u kojima je moguće promijeniti jezik i temu aplikacije.

### Tech stack
- Kotlin
- Kotlin Coroutines
- Jetpack libraries - RecyclerView, ViewModel, Room, ktx extensions, etc.
- Koin - dependency injection
- Coil - image loading
- ...
