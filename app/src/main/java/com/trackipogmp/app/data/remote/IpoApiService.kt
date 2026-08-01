package com.trackipogmp.app.data.remote

import com.trackipogmp.app.data.model.IpoItem
import retrofit2.http.GET

interface IpoApiService {
    @GET("ipos.json")
    suspend fun getIpos(): List<IpoItem>
}
