package com.db.presentation.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.db.data.tools.DateUtils
import com.db.data.tools.Ext.setTextColorByRes
import com.db.presentation.R
import java.util.*

class CalendarAdapter(private val itemClick: (Date) -> Unit) :
    RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {
    var selected = 0

    private val items by lazy {
        DateUtils.calendarDates(0, 365, true)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val day: TextView = view.findViewById(R.id.txt_day)
        private val month: TextView = view.findViewById(R.id.txt_month)
        private val case: LinearLayout = view.findViewById(R.id.case_date)

        fun bind(date: Date) {
            if (adapterPosition == selected) {
                day.setTextColorByRes(R.color.accent)
                month.setTextColorByRes(R.color.accent)
            } else {
                day.setTextColor(Color.BLACK)
                month.setTextColor(Color.BLACK)
            }
            month.text = DateUtils.getMonthName(date).substringBefore(".")
            day.text = DateUtils.getDayNumber(date)

            itemView.setOnClickListener {
                itemClick(date)
                selected = adapterPosition
                notifyDataSetChanged()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calendar, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

}