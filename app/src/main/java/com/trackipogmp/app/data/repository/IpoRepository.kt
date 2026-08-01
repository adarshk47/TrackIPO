package com.trackipogmp.app.data.repository

import com.trackipogmp.app.data.local.IpoDao
import com.trackipogmp.app.data.model.IpoItem
import com.trackipogmp.app.data.remote.IpoApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IpoRepository @Inject constructor(
    private val apiService: IpoApiService,
    private val ipoDao: IpoDao
) {
    val allIpos: Flow<List<IpoItem>> = ipoDao.getAllIpos()

    suspend fun refreshIpos() {
        try {
            val ipos = apiService.getIpos()
            ipoDao.insertIpos(ipos)
        } catch (e: Exception) {
            // Handle error
        }
    }
}
