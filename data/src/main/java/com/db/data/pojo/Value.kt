package com.db.data.pojo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Value(
    @Json(name = "default")
    val default: Boolean?,
    @Json(name = "label")
    val label: String?,
    @Json(name = "selected")
    val selected: Boolean?,
    @Json(name = "value")
    val value: Any?
)