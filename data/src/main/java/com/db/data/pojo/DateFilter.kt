package com.db.data.pojo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DateFilter(
    @Json(name = "date_from")
    val dateFrom: String?,
    @Json(name = "date_to")
    val dateTo: String?
)