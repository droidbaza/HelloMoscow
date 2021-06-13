package com.db.data.pojo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Kind(
    @Json(name = "id")
    val id: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "type")
    val type: Int?
)