package hr.vub.itepavac.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.navigation
import hr.vub.itepavac.ui.artists.ArtistsOverview
import hr.vub.itepavac.ui.events.Events
import hr.vub.itepavac.ui.events.details.EventDetails
import hr.vub.itepavac.ui.events.edit.EditEvent
import hr.vub.itepavac.ui.menu.Menu
import hr.vub.itepavac.ui.settings.Settings

sealed class RootScreen(val route: String) {

    object Events : RootScreen("events")
    object Menu : RootScreen("menu")
}

sealed class Screen(private val route: String) {

    fun createRoute(root: RootScreen): String = "${root.route}/$route"

    object Events : Screen("events")
    object Menu : Screen("menu")

    object EventDetails : Screen("event/{eventId}") {

        fun createRoute(root: RootScreen, eventId: Int): String {
            return "${root.route}/event/${eventId}"
        }
    }

    object EventAdd : Screen("event/add")

    object EventEdit : Screen("event/{eventId}/edit") {

        fun createRoute(root: RootScreen, eventId: Int): String {
            return "${root.route}/event/${eventId}/edit"
        }
    }

    object ArtistsOverview : Screen("artists?select={select}") {

        fun createRoute(root: RootScreen, select: Boolean): String {
            return "${root.route}/artists?select=${select}"
        }
    }

    object Settings : Screen("settings")
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = RootScreen.Events.route,
        modifier = modifier
    ) {
        addEventsRoot(navController)
        addMenuRoot(navController)
    }
}

private fun NavGraphBuilder.addEventsRoot(navController: NavHostController) {
    navigation(
        route = RootScreen.Events.route,
        startDestination = Screen.Events.createRoute(RootScreen.Events)
    ) {
        addEvents(navController, RootScreen.Events)
        addEventDetails(navController, RootScreen.Events)
        addEventEdit(navController, RootScreen.Events)
        addEventAdd(navController, RootScreen.Events)
        addArtistsOverview(navController, RootScreen.Events)
    }
}

private fun NavGraphBuilder.addMenuRoot(navController: NavHostController) {
    navigation(
        route = RootScreen.Menu.route,
        startDestination = Screen.Menu.createRoute(RootScreen.Menu)
    ) {
        addMenu(navController, RootScreen.Menu)
        addArtistsOverview(navController, RootScreen.Menu)
        addEventAdd(navController, RootScreen.Menu)
        addSettings(navController, RootScreen.Menu)
    }
}

private fun NavGraphBuilder.addEvents(
    navController: NavHostController,
    root: RootScreen
) {
    composable(Screen.Events.createRoute(root)) {
        Events(
            onNavigateToDetails = { navController.navigate(Screen.EventDetails.createRoute(root, it)) },
            onAddNewEvent = { navController.navigate(Screen.EventAdd.createRoute(root)) }
        )
    }
}

private fun NavGraphBuilder.addEventDetails(
    navController: NavHostController,
    root: RootScreen
) {
    composable(
        route = Screen.EventDetails.createRoute(root),
        arguments = listOf(
            navArgument("eventId") { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val eventId = backStackEntry.arguments?.getInt("eventId") ?: error("eventId not provided!")
        EventDetails(
            onNavigateUp = { navController.navigateUp() },
            onEditEvent = { navController.navigate(Screen.EventEdit.createRoute(root, eventId)) }
        )
    }
}

private fun NavGraphBuilder.addEventAdd(
    navController: NavHostController,
    root: RootScreen
) {
    composable(
        route = Screen.EventAdd.createRoute(root),
    ) {
        EditEvent(
            onNavigateUp = { navController.navigateUp() },
            onSelectArtist = { navController.navigate(Screen.ArtistsOverview.createRoute(root, select = true)) }
        )
    }
}

private fun NavGraphBuilder.addEventEdit(
    navController: NavHostController,
    root: RootScreen
) {
    composable(
        route = Screen.EventEdit.createRoute(root),
        arguments = listOf(
            navArgument("eventId") { type = NavType.IntType }
        )
    ) {
        EditEvent(
            onNavigateUp = { navController.navigateUp() },
            onSelectArtist = { navController.navigate(Screen.ArtistsOverview.createRoute(root, select = true)) }
        )
    }
}

private fun NavGraphBuilder.addMenu(
    navController: NavHostController,
    root: RootScreen
) {
    composable(Screen.Menu.createRoute(root)) {
        Menu(
            onNavigateToArtistsOverview = { navController.navigate(Screen.ArtistsOverview.createRoute(root)) },
            onNavigateToNewEvent = { navController.navigate(Screen.EventAdd.createRoute(root)) },
            onNavigateToSettings = { navController.navigate(Screen.Settings.createRoute(root)) }
        )
    }
}

private fun NavGraphBuilder.addArtistsOverview(
    navController: NavHostController,
    root: RootScreen
) {
    composable(
        route = Screen.ArtistsOverview.createRoute(root),
        arguments = listOf(navArgument("select") { defaultValue = false })
    ) {
        ArtistsOverview(
            onNavigateUp = { navController.navigateUp() }
        )
    }
}

private fun NavGraphBuilder.addSettings(
    navController: NavHostController,
    root: RootScreen
) {
    composable(Screen.Settings.createRoute(root)) {
        Settings(
            onNavigateUp = { navController.navigateUp() }
        )
    }
}
