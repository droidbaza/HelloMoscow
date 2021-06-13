package com.db.data.pojo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class District(
    @Json(name = "area_ids")
    val areaIds: List<Int>?,
    @Json(name = "id")
    val id: Int?
)