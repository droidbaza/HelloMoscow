package com.db.data.api

import com.db.data.pojo.responses.ResponseEvent
import com.db.data.pojo.responses.ResponseEvents
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEvents {

    //https://www.mos.ru/api/newsfeed/v4/frontend/json/ru/afisha?spheres=78299&fields=spheres&expand=spheres
    companion object {
        const val FULL_EXPAND =
            ""
    }

    //https://www.mos.ru/api/newsfeed/v4/frontend/json/ru/afisha/98016257?expand=icon, tags, spheres, themes, auditories, url, spots, districts,foundation
    @GET("newsfeed/v4/frontend/json/ru/afisha/{id}?$FULL_EXPAND")
    suspend fun eventDetailsAsync(@Path("id") id: Int): Response<ResponseEvent>

    //
    @GET("api/newsfeed/v4/frontend/json/ru/afisha?sort=occurrences.date_to,-occurrences.date_from")
    suspend fun eventsBySpheresAsync(
        @Query("spheres") spheres: String?,
        @Query("filter") filter: String,
        @Query("page") page: Int?,
    ): Response<ResponseEvents>

    /*  //
      @GET("v1/search/afisha?per-page=50&$FULL_EXPAND")
      suspend fun searchEventsAsync(@Query("q") query: String): Response<ResponseSearch>*/
}