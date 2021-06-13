package com.db.data.pojo.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.db.data.pojo.Category
import com.db.data.pojo.Filter
import com.db.data.pojo.Info
import com.db.data.pojo.Meta
import com.db.data.pojo.Search

@JsonClass(generateAdapter = true)
data class ResponseSearch(
    @Json(name = "categories")
    val categories: List<Category>?,
    @Json(name = "filters")
    val filters: List<Filter>?,
    @Json(name = "info")
    val info: Info?,
    @Json(name = "_meta")
    val meta: Meta?,
    @Json(name = "results")
    val results: List<Search>?
)