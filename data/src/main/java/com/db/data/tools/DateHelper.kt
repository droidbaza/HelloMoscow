package com.rusatom.data.tools

import java.text.SimpleDateFormat
import java.util.*

object DateHelper  {

    fun dateFromTimeStamp(timestamp: Int): String {
        return SimpleDateFormat(
            "dd MMMM, HH:mm",
            Locale.getDefault()
        ).format(Date(timestamp.toLong() * 1000))
    }

    fun dateForComment(timestamp: Int): String {
        return SimpleDateFormat(
            "dd MMMM, HH:mm",
            Locale.getDefault()
        ).format(Date(timestamp.toLong() * 1000))
    }

    fun dateForEvent(timestamp: Int?, default: Int): String {
        return if (timestamp != null) {
            SimpleDateFormat(
                "dd MMMM, HH:mm",
                Locale.getDefault()
            ).format(Date(timestamp.toLong() * 1000))
        } else {
            SimpleDateFormat(
                "dd MMMM, HH:mm",
                Locale.getDefault()
            ).format(Date(default.toLong() * 1000))
        }
    }


    fun currentDate(): String {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(currentDate)
    }

    fun formatToDay(date: String?): String {
        val input = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val output = SimpleDateFormat("d", Locale.getDefault())
        var day = ""
        try {
            input.parse(date ?: "")?.let {
                day = output.format(it)
            }
        } catch (ex: Exception) {
            day = ""
        }
        return day
    }

    fun formatToMonth(date: String?): String {
        val input = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val output = SimpleDateFormat("LLLL", Locale.getDefault())
        var month = ""
        try {
            input.parse(date ?: "")?.let {
                month = output.format(it)
            }
        } catch (ex: Exception) {
            month = ""
        }
        if (month.length > 3) {
            month = month.substring(0,3)
        }

        return month
    }

}