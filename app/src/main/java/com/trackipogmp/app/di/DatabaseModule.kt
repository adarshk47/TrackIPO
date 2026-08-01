package com.trackipogmp.app.di

import android.content.Context
import androidx.room.Room
import com.trackipogmp.app.data.local.IpoDao
import com.trackipogmp.app.data.local.IpoDatabase
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
    fun provideDatabase(@ApplicationContext context: Context): IpoDatabase {
        return Room.databaseBuilder(
            context,
            IpoDatabase::class.java,
            "ipo_db"
        ).build()
    }

    @Provides
    fun provideIpoDao(database: IpoDatabase): IpoDao {
        return database.ipoDao()
    }
}
