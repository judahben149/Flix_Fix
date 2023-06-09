package com.judahben149.flixfix.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.judahben149.flixfix.data.local.entity.MovieEntityRemoteKey

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteKeys(movieEntityRemoteKeys: List<MovieEntityRemoteKey>)

    @Query("SELECT * FROM remote_keys WHERE id = :id")
    suspend fun getAllRemoteKeys(id: Int): MovieEntityRemoteKey?

    @Query("DELETE FROM remote_keys")
    suspend fun deleteAllRemoteKeys()
}