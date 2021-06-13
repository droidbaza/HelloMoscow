package com.db.data.pojo

import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.rusatom.data.tools.DateHelper
import com.db.data.Contract
import com.db.domain.models.ModelEvent

object Mapper {

    fun mapToModelEvent(
        id: Int,
        title: String?,
        dateTimestamp: Int,
        description: String?,
        imageSrc: String?
    ): ModelEvent {
        val date = DateHelper.dateFromTimeStamp(dateTimestamp)
        return ModelEvent(
            id, title ?: "", description.formatText(), date, "", "", "${Contract.BASE_URL}${imageSrc?.substringAfter("/")}",
            emptyList(), "", null, false
        )

    }

    fun String?.formatText(): Spanned {
        return HtmlCompat.fromHtml(this ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}