package com.db.data.pojo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Filter(
    @Json(name = "date_from_limit")
    val dateFromLimit: Int?,
    @Json(name = "date_to_limit")
    val dateToLimit: Int?,
    @Json(name = "enable")
    val enable: Boolean?,
    @Json(name = "isApplied")
    val isApplied: Boolean?,
    @Json(name = "isSearchInput")
    val isSearchInput: Boolean?,
    @Json(name = "label")
    val label: String?,
    @Json(name = "label_variants")
    val labelVariants: List<String>?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "values")
    val values: List<Value>?
)