package com.db.data.pojo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meta(
    @Json(name = "currentPage")
    val currentPage: Int?,
    @Json(name = "max_score")
    val maxScore: Any?,
    @Json(name = "pageCount")
    val pageCount: Int?,
    @Json(name = "perPage")
    val perPage: Int?,
    @Json(name = "search_name")
    val searchName: String?,
    @Json(name = "totalCount")
    val totalCount: Int?
)