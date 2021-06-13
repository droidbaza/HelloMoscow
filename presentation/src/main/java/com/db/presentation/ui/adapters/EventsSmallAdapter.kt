package com.db.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.db.domain.models.ModelEvent
import com.db.presentation.R

class EventsSmallAdapter(private val itemClick: (Int) -> Unit) :
    RecyclerView.Adapter<EventsSmallAdapter.ViewHolder>() {

    private val items by lazy {
        mutableListOf<ModelEvent>()
    }

    fun update(newList: List<ModelEvent>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title: TextView = view.findViewById(R.id.txt_event_title)
        private val image: ImageView = view.findViewById(R.id.it_img)

        fun bind(event: ModelEvent) {
            title.text = event.title
            itemView.setOnClickListener { itemClick(adapterPosition) }

            Glide.with(image.context).setDefaultRequestOptions(
                RequestOptions().apply {
                    diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    format(DecodeFormat.PREFER_RGB_565)
                    dontAnimate()
                    centerCrop()
                    priority(Priority.HIGH)
                }
            ).load(event.image).thumbnail(0.2f).into(image)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event_small, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }


}