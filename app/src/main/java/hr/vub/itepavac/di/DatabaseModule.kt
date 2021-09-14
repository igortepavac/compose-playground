package hr.vub.itepavac.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hr.vub.itepavac.data.database.EventXyzDatabase
import hr.vub.itepavac.data.database.dao.ArtistDao
import hr.vub.itepavac.data.database.dao.EventDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): EventXyzDatabase {
        return Room.databaseBuilder(context, EventXyzDatabase::class.java, "events.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}

@InstallIn(SingletonComponent::class)
@Module
class DatabaseDaoModule {

    @Provides
    @Singleton
    fun provideArtistDao(database: EventXyzDatabase): ArtistDao = database.artistDao()

    @Provides
    @Singleton
    fun provideEventDao(database: EventXyzDatabase): EventDao = database.eventsDao()
}
