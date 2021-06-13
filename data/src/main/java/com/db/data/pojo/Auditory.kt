package com.db.data.pojo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Auditory(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "title")
    val title: String?
)