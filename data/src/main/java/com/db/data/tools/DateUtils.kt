package com.db.data.tools

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun today(): Date {
        return Calendar.getInstance().time
    }

    fun tomorrow(): Date {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, 1)
        return cal.time
    }

    fun parseDateForRequest(date: Date): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
    }

    fun parseDateEndForRequest(date: Date): String {
        date.hours = 23
        date.minutes = 59
        date.seconds = 59
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(date)
    }

    fun getDayName(date: Date): String =
        SimpleDateFormat("EEEE", Locale.getDefault()).format(date)

    fun getDay3LettersName(date: Date): String =
        SimpleDateFormat("EE", Locale.getDefault()).format(date)

    fun getDay1LetterName(date: Date): String =
        SimpleDateFormat("EEEEE", Locale.getDefault()).format(date)

    fun getMonthName(date: Date): String =
        SimpleDateFormat("MMM", Locale.getDefault()).format(date)

    fun getYear(date: Date): String =
        SimpleDateFormat("yyyy", Locale.getDefault()).format(date)


    fun getDayNumber(date: Date): String =
        SimpleDateFormat("dd", Locale.getDefault()).format(date)

    fun getNumberOfWeek(date: Date): String {
        val cal = Calendar.getInstance()
        cal.time = date
        return cal[Calendar.WEEK_OF_YEAR].toString()
    }

    private fun getFutureDates(count: Int): MutableList<Date> {
        val futureDateList = mutableListOf<Date>()
        val cal = Calendar.getInstance(Locale.getDefault())
        for (i in 0 until count) {
            cal.add(Calendar.DATE, 1)
            futureDateList.add(cal.time)
        }
        return futureDateList
    }

    private fun getPastDates(count: Int): MutableList<Date> {
        val pastDateList = mutableListOf<Date>()
        val cal = Calendar.getInstance(Locale.getDefault())
        for (i in 0 until count) {
            cal.add(Calendar.DATE, -1)
            pastDateList.add(cal.time)
        }
        return pastDateList
    }

    fun calendarDates(pastDays: Int, futureDays: Int, includeCurrentDate: Boolean): List<Date> {
        val futureList =
            getFutureDates(
                futureDays
            )
        val cal = Calendar.getInstance(Locale.getDefault())
        val pastList = getPastDates(
            pastDays
        ).reversed()
        return if (includeCurrentDate) pastList + cal.time + futureList else pastList + futureList
    }

}