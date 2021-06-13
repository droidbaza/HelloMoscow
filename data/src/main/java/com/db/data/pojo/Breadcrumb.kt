package com.db.data.pojo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Breadcrumb(
    @Json(name = "@context")
    val context: String?,
    @Json(name = "itemListElement")
    val itemListElement: List<ItemElement>?,
    @Json(name = "@type")
    val type: String?
)