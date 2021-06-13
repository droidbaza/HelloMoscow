package com.db.data.pojo.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.db.data.pojo.Meta

@JsonClass(generateAdapter = true)
data class ResponseEvents(
	@Json(name = "items")
	val items: List<ResponseEvent>,
	@Json(name = "_meta")
	val _meta: Meta
)