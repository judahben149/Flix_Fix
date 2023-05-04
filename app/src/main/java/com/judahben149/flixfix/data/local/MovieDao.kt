package com.judahben149.flixfix.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.judahben149.flixfix.data.local.entity.MovieEntity
import com.judahben149.flixfix.data.local.entity.MovieEntityRemoteKey

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movieEntity: List<MovieEntity>)

    @Query("SELECT * FROM MovieEntity")
    fun getAllMovies(): PagingSource<Int, MovieEntity>

    @Query("DELETE FROM MovieEntity")
    suspend fun deleteAllArticles()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteKeys(movieEntityRemoteKeys: List<MovieEntityRemoteKey>)

    @Query("SELECT * FROM MovieEntityRemoteKey WHERE id = :id")
    fun getAllRemoteKeys(id: String): MovieEntityRemoteKey?

    @Query("DELETE FROM MovieEntityRemoteKey")
    fun deleteAllRemoteKeys()
}