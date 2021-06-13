package com.db.data.pojo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    @Json(name = "big")
    val big: Big?,
    @Json(name = "copyright")
    val copyright: Any?,
    @Json(name = "download")
    val download: Download?,
    @Json(name = "id")
    val id: Long?,
    @Json(name = "middle")
    val middle: Middle?,
    @Json(name = "original")
    val original: Original?,
    @Json(name = "show")
    val show: Show?,
    @Json(name = "small")
    val small: Small?,
    @Json(name = "thumb")
    val thumb: Thumb?,
    @Json(name = "title")
    val title: Any?
)