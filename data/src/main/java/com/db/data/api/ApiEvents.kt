package com.db.data.api

import com.db.data.pojo.responses.ResponseEvent
import com.db.data.pojo.responses.ResponseEvents
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEvents {


    @GET("newsfeed/v4/frontend/json/ru/afisha/{id}")
    suspend fun eventDetailsAsync(@Path("id") id: Int): Response<ResponseEvent>

    @GET("api/newsfeed/v4/frontend/json/ru/afisha?sort=occurrences.date_to,-occurrences.date_from")
    suspend fun eventsBySpheresAsync(
        @Query("spheres") spheres: String?,
        @Query("filter") filter: String,
        @Query("page") page: Int?,
    ): Response<ResponseEvents>

}