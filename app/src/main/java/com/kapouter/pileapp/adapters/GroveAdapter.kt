package com.kapouter.pileapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kapouter.pileapp.R
import com.kapouter.pileapp.model.GrovePlant
import com.kapouter.pileapp.model.Image
import kotlinx.android.synthetic.main.item_grove.view.*

class GroveAdapter :
    PagedListAdapter<GrovePlant, GroveAdapter.ViewHolder>(GrovePlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_grove, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        fun bind(item: GrovePlant?) {
            v.apply {
                name.text = item?.name ?: item?.scientificName
                scientificName.text = item?.scientificName

                Glide.with(v)
                    .load(findFirstNonSvgUrl(item?.images))
                    .centerCrop()
                    .placeholder(R.drawable.ic_flower)
                    .into(image)

            }
        }

        private fun findFirstNonSvgUrl(images: List<Image>?): String? {
            if (images == null || images.isEmpty()) return null
            return images.find { !it.url.contains("svg", true) }?.url ?: images[0].url
        }
    }
}

private class GrovePlantDiffCallback : DiffUtil.ItemCallback<GrovePlant>() {

    override fun areItemsTheSame(oldItem: GrovePlant, newItem: GrovePlant): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GrovePlant, newItem: GrovePlant): Boolean {
        return oldItem == newItem
    }
}