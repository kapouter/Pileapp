package com.kapouter.pileapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kapouter.pileapp.R
import com.kapouter.pileapp.model.GrovePlant
import kotlinx.android.synthetic.main.item_plant.view.*

class GroveAdapter :
    PagedListAdapter<GrovePlant, GroveAdapter.ViewHolder>(GrovePlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_plant, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        fun bind(item: GrovePlant?) {
            v.apply {
                plantName.text = item?.name
                plantDescription.text = item?.scientificName
                addPlant.visibility = GONE
            }
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