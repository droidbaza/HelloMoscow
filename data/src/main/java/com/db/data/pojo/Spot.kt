package com.db.data.pojo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Spot(
    @Json(name = "address")
    val address: String?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "created_at_timestamp")
    val createdAtTimestamp: Int?,
    @Json(name = "ebs_id")
    val ebsId: Int?,
    @Json(name = "ebs_title")
    val ebsTitle: String?,
    @Json(name = "foundation_id")
    val foundationId: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "lat")
    val lat: String?,
    @Json(name = "lon")
    val lon: String?,
    @Json(name = "place_type_id")
    val placeTypeId: Int?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "updated_at")
    val updatedAt: String?,
    @Json(name = "updated_at_timestamp")
    val updatedAtTimestamp: Int?
)