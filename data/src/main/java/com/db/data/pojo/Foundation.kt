package com.db.data.pojo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Foundation(
    @Json(name = "address")
    val address: String?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "ebs_id")
    val ebsId: Int?,
    @Json(name = "ebs_title")
    val ebsTitle: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "image")
    val image: Image?,
    @Json(name = "lang")
    val lang: String?,
    @Json(name = "phone")
    val phone: String?,
    @Json(name = "place_type_id")
    val placeTypeId: Int?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "updated_at")
    val updatedAt: String?
)