package com.trackipogmp.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.trackipogmp.app.data.model.IpoItem

@Database(entities = [IpoItem::class], version = 1)
abstract class IpoDatabase : RoomDatabase() {
    abstract fun ipoDao(): IpoDao
}
