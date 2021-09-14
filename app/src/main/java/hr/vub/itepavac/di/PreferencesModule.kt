package hr.vub.itepavac.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.vub.itepavac.data.preferences.EventXyzPreferences
import hr.vub.itepavac.data.preferences.LocalPreferences
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class PreferencesModule {

    @Binds
    @Singleton
    abstract fun bindPreferences(prefs: LocalPreferences) : EventXyzPreferences
}
