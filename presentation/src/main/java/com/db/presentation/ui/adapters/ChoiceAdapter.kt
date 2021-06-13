package com.db.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.db.domain.models.ModelChoice
import com.db.presentation.R

class ChoiceAdapter(private val itemClick: (String) -> Unit) :
    RecyclerView.Adapter<ChoiceAdapter.ViewHolder>() {

    var items: MutableList<ModelChoice> = mutableListOf()

    fun update(newList: List<ModelChoice>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title: TextView = view.findViewById(R.id.txt_choice_title)
        private val image: ImageView = view.findViewById(R.id.img_choice)

        fun bind(choice: ModelChoice) {
            if (choice.image == null) {
                image.setImageResource(R.drawable.ic_mic_black_24dp)
            } else {
                image.setImageDrawable(null)
            }
            title.text = choice.title
            itemView.setOnClickListener {
                itemClick(choice.title)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_choice, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

}