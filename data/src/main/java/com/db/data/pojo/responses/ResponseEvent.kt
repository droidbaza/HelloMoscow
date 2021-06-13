package com.db.data.pojo.responses


import com.rusatom.data.tools.DateHelper
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.db.data.Contract.BASE_URL
import com.db.data.pojo.*
import com.db.domain.models.ModelEvent

@JsonClass(generateAdapter = true)
data class ResponseEvent(
    @Json(name = "active_from")
    val activeFrom: Any?,
    @Json(name = "active_from_timestamp")
    val activeFromTimestamp: Any?,
    @Json(name = "active_to")
    val activeTo: Any?,
    @Json(name = "active_to_timestamp")
    val activeToTimestamp: Any?,
    @Json(name = "auditories")
    val auditories: List<Auditory>?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "created_at_timestamp")
    val createdAtTimestamp: Int?,
    @Json(name = "date")
    val date: String?,
    @Json(name = "date_from")
    val dateFrom: String?,
    @Json(name = "date_from_timestamp")
    val dateFromTimestamp: Int?,
    @Json(name = "date_timestamp")
    val dateTimestamp: Int,
    @Json(name = "date_to")
    val dateTo: String?,
    @Json(name = "date_to_timestamp")
    val dateToTimestamp: Int?,
    @Json(name = "districts")
    val districts: List<District>?,
    @Json(name = "ebs_agent_uid")
    val ebsAgentUid: String?,
    @Json(name = "ebs_id")
    val ebsId: Int?,
    @Json(name = "ebs_title")
    val ebsTitle: String?,
    @Json(name = "ebs_type")
    val ebsType: String?,
    @Json(name = "foundation")
    val foundation: Foundation?,
    @Json(name = "free")
    val free: Int?,
    @Json(name = "has_image")
    val hasImage: Int?,
    @Json(name = "icon")
    val icon: Any?,
    @Json(name = "icon_id")
    val iconId: Any?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: Image?,
    @Json(name = "is_oiv_publication")
    val isOivPublication: Int?,
    @Json(name = "is_powered")
    val isPowered: Int?,
    @Json(name = "kind")
    val kind: Kind?,
    @Json(name = "label")
    val label: Any?,
    @Json(name = "lang")
    val lang: String?,
    @Json(name = "oiv_id")
    val oivId: Int?,
    @Json(name = "organizations")
    val organizations: List<Int>?,
    @Json(name = "published_at")
    val publishedAt: String?,
    @Json(name = "restriction")
    val restriction: Restriction?,
    @Json(name = "search")
    val search: Int?,
    @Json(name = "spheres")
    val spheres: List<Sphere>?,
    @Json(name = "spots")
    val spots: List<Spot>?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "tags")
    val tags: List<Tag>?,
    @Json(name = "text")
    val text: String?,
    @Json(name = "themes")
    val themes: List<Any>?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "updated_at")
    val updatedAt: String?,
    @Json(name = "updated_at_timestamp")
    val updatedAtTimestamp: Int?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "ya_rss")
    val yaRss: Int?
) {
    fun mapToModelEvent(): ModelEvent {
        val date = DateHelper.dateFromTimeStamp(dateTimestamp)
        return ModelEvent(
            id,
            title ?: "",
            text ?: "",
            date,
            "",
            "",
            "${BASE_URL}upload${image?.original?.src}",
            emptyList(),
            "",
            null,
            false
        )

    }
}