package com.kapouter.pileapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kapouter.pileapp.R
import com.kapouter.pileapp.data.Plant
import kotlinx.android.synthetic.main.item_plant.view.*

interface OnAddItemListener {
    fun onAddItem(item: Plant)
}

class PlantAdapter(private val onAddListener: OnAddItemListener? = null) :
    ListAdapter<Plant, PlantAdapter.ViewHolder>(PlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_plant, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onAddListener)
    }

    class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        fun bind(item: Plant, onAddListener: OnAddItemListener?) {
            v.apply {
                plantName.text = item.name
                plantDescription.text = item.description
                if (onAddListener != null) {
                    addPlant.visibility = VISIBLE
                    addPlant.setOnClickListener { onAddListener.onAddItem(item) }
                } else
                    addPlant.visibility = GONE
            }
        }
    }
}

private class PlantDiffCallback : DiffUtil.ItemCallback<Plant>() {

    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }
}