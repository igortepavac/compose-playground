package hr.vub.itepavac.ui.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import hr.vub.itepavac.R
import hr.vub.itepavac.ui.theme.EventXyzTheme

private sealed class BottomNavItem(
    val screen: RootScreen,
    @StringRes val titleResId: Int,
    val icon: @Composable () -> Painter
) {
    object Events : BottomNavItem(RootScreen.Events, R.string.home_nav_events, { EventXyzTheme.icons.calendar } )
    object Menu : BottomNavItem(RootScreen.Menu, R.string.home_nav_menu, { EventXyzTheme.icons.menu })
}

@Composable
fun Home() {
    val navController = rememberNavController()
    val screens = remember { listOf(BottomNavItem.Events, BottomNavItem.Menu) }
    Scaffold(
        bottomBar = { BottomBar(navController, screens) }
    ) { innerPadding ->
        AppNavigation(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun BottomBar(navController: NavController, items: List<BottomNavItem>) {
    BottomNavigation(backgroundColor = MaterialTheme.colors.background) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { item ->
            BottomNavigationItem(
                selectedContentColor = EventXyzTheme.colorPalette.AppPurple,
                icon = {
                    Icon(
                        painter = item.icon(),
                        contentDescription = stringResource(item.titleResId)
                    )
                },
                label = { Text(stringResource(item.titleResId)) },
                selected = currentDestination?.hierarchy?.any { it.route == item.screen.route } == true,
                onClick = {
                    navController.navigate(item.screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
