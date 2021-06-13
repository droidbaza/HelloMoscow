package com.db.data.pojo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rank(
    @Json(name = "total_rank")
    val totalRank: Double?
)