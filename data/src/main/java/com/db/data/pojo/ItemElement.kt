package com.db.data.pojo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemElement(
    @Json(name = "item")
    val item: Item?,
    @Json(name = "position")
    val position: Int?,
    @Json(name = "@type")
    val type: String?
)