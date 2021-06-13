package com.db.data.pojo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Search(
    @Json(name = "address_id")
    val addressId: Int?,
    @Json(name = "address_title")
    val addressTitle: String?,
    @Json(name = "amp")
    val amp: String?,
    @Json(name = "auditory_id")
    val auditoryId: Int?,
    @Json(name = "breadcrumb")
    val breadcrumb: Breadcrumb?,
    @Json(name = "category")
    val category: String?,
    @Json(name = "category_url")
    val categoryUrl: String?,
    @Json(name = "created_at")
    val createdAt: Int?,
    @Json(name = "date")
    val date: Int?,
    @Json(name = "date_from")
    val dateFrom: Int?,
    @Json(name = "date_from_timestamp")
    val dateFromTimestamp: Int?,
    @Json(name = "date_to")
    val dateTo: Int?,
    @Json(name = "date_to_timestamp")
    val dateToTimestamp: Int?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "image")
    val image: String?,
    @Json(name = "importance")
    val importance: Int?,
    @Json(name = "index")
    val index: String?,
    @Json(name = "place_id")
    val placeId: Int?,
    @Json(name = "place_lat")
    val placeLat: Double?,
    @Json(name = "place_lon")
    val placeLon: Double?,
    @Json(name = "place_title")
    val placeTitle: String?,
    @Json(name = "rank")
    val rank: Rank?,
    @Json(name = "skind")
    val skind: String?,
    @Json(name = "sphere_id")
    val sphereId: Int?,
    @Json(name = "sphere_title")
    val sphereTitle: String?,
    @Json(name = "text")
    val text: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "url")
    val url: String?
)