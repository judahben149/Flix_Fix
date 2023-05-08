package com.judahben149.flixfix.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.judahben149.flixfix.data.local.entity.MovieEntity
import com.judahben149.flixfix.data.local.entity.MovieEntityRemoteKey
import com.judahben149.flixfix.data.local.entity.MovieListEntity

@Database(entities = [MovieListEntity::class, MovieEntityRemoteKey::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}