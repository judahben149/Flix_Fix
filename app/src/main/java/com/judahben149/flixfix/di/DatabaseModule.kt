package com.judahben149.flixfix.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.judahben149.flixfix.data.local.MovieDao
import com.judahben149.flixfix.data.local.MovieDatabase
import com.judahben149.flixfix.utils.Constants.MOVIE_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            MOVIE_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun providesMovieDao(database: MovieDatabase): MovieDao {
        return database.movieDao()
    }

}