package hr.vub.itepavac.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import hr.vub.itepavac.ui.shared.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface EventXyzPreferences {

    suspend fun setDidInitializeDatabase(flag: Boolean)

    suspend fun setTheme(theme: Theme)

    suspend fun setLanguage(language: Language)

    fun userPreferencesFlow(): Flow<UserPreferences>
}

data class UserPreferences(
    val didInitializeDatabase: Boolean,
    val theme: Theme,
    val language: Language
) {
    companion object {
        fun empty() = UserPreferences(
            didInitializeDatabase = false,
            theme = Theme.SYSTEM,
            language = Language.ENGLISH
        )
    }
}

class LocalPreferences @Inject constructor(
    @ApplicationContext private val context: Context,
    private val logger: Logger
) : EventXyzPreferences {

    private val Context.dataStore by preferencesDataStore(PREFS_NAME)

    private val dataStore = context.dataStore

    override suspend fun setDidInitializeDatabase(flag: Boolean) {
        dataStore.edit { preferences -> preferences[KEY_DATABASE_INIT] = flag }
    }

    override suspend fun setTheme(theme: Theme) {
        dataStore.edit { preferences -> preferences[KEY_THEME] = theme.ordinal }
    }

    override suspend fun setLanguage(language: Language) {
        dataStore.edit { preferences -> preferences[KEY_LANGUAGE] = language.ordinal }
    }

    override fun userPreferencesFlow(): Flow<UserPreferences> {
        return context.dataStore.data
            .catch { e ->
                logger.log("Failure in datastore preferences", e)
                emit(emptyPreferences())
            }
            .map { preferences ->
                UserPreferences(
                    didInitializeDatabase = preferences[KEY_DATABASE_INIT] ?: false,
                    theme = Theme.values()[preferences[KEY_THEME] ?: Theme.SYSTEM.ordinal],
                    language = Language.values()[preferences[KEY_LANGUAGE] ?: Language.ENGLISH.ordinal]
                )
            }
    }

    companion object PreferenceKeys {
        private const val PREFS_NAME = "EVENT_XYZ_USER_PREFS"

        private val KEY_DATABASE_INIT = booleanPreferencesKey("KEY_DATABASE_INIT")
        private val KEY_THEME = intPreferencesKey("KEY_THEME")
        private val KEY_LANGUAGE = intPreferencesKey("KEY_LANGUAGE")
    }
}
