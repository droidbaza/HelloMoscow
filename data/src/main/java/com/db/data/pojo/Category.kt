package com.db.data.pojo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Category(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "disabled")
    val disabled: Boolean?,
    @Json(name = "label")
    val label: String?,
    @Json(name = "path")
    val path: String?,
    @Json(name = "position")
    val position: Int?
)