package com.db.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ModelEvent(
    var id: Int,
    var title: String,
    var description: CharSequence,
    var clockTime: String,
    var finishDate: String,
    var type: String?,
    val image: String = "",
    var images: List<String> = mutableListOf(),
    var cost: String?,
    var address: String? = null,
    var favorited: Boolean,
) : Parcelable
