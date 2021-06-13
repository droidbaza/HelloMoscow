package com.db.data.pojo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Info(
    @Json(name = "fuzzy")
    val fuzzy: Int?,
    @Json(name = "query")
    val query: String?,
    @Json(name = "query_orig")
    val queryOrig: String?,
    @Json(name = "query_primordial")
    val queryPrimordial: String?,
    @Json(name = "status")
    val status: String?
)