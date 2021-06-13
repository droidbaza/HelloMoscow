package com.db.data.pojo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Download(
    @Json(name = "height")
    val height: Int?,
    @Json(name = "id")
    val id: Long?,
    @Json(name = "src")
    val src: String?,
    @Json(name = "title")
    val title: Any?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "width")
    val width: Int?
)