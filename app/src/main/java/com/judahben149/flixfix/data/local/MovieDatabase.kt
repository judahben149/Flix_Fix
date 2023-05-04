package com.judahben149.flixfix.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.judahben149.flixfix.data.local.entity.MovieEntity
import com.judahben149.flixfix.data.local.entity.MovieEntityRemoteKey

@Database(entities = [MovieEntity::class, MovieEntityRemoteKey::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

}