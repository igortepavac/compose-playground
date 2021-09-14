package hr.vub.itepavac.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModelProvider
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import hr.vub.itepavac.data.preferences.EventXyzPreferences
import hr.vub.itepavac.data.preferences.Theme
import hr.vub.itepavac.data.preferences.UserPreferences
import hr.vub.itepavac.ui.shared.ProgressIndicatorWrapper
import hr.vub.itepavac.ui.theme.EventXyzTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    @Inject lateinit var preferences: EventXyzPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setContent {
            EventXyzTheme(darkTheme = preferences.useDarkTheme()) {
                val systemUiController = rememberSystemUiController()
                val systemBarsColor = EventXyzTheme.colors.background
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = systemBarsColor
                    )
                }

                val viewState = viewModel.viewState.collectAsState()
                Surface(color = MaterialTheme.colors.background) {
                    viewState.let { state ->
                        when (state.value) {
                            is MainViewState.Loading -> ProgressIndicatorWrapper()
                            is MainViewState.Ready -> Home()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EventXyzPreferences.useDarkTheme(): Boolean {
    val userPreferences = userPreferencesFlow().collectAsState(initial = UserPreferences.empty())
    return when (userPreferences.value.theme) {
        Theme.LIGHT -> false
        Theme.DARK -> true
        else -> isSystemInDarkTheme()
    }
}
