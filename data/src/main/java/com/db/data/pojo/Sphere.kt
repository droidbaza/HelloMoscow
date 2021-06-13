package com.db.data.pojo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sphere(
    @Json(name = "activated")
    val activated: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "priority")
    val priority: Int?,
    @Json(name = "special")
    val special: Int?,
    @Json(name = "title")
    val title: String?
)