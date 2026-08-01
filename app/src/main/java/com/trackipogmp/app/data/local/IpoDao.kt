package com.trackipogmp.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.trackipogmp.app.data.model.IpoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface IpoDao {
    @Query("SELECT * FROM ipos")
    fun getAllIpos(): Flow<List<IpoItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIpos(ipos: List<IpoItem>)

    @Query("DELETE FROM ipos")
    suspend fun clearAll()
}
