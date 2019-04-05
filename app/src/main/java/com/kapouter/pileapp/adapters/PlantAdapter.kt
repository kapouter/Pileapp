package com.kapouter.pileapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kapouter.pileapp.R
import com.kapouter.pileapp.databinding.ItemPlantBinding
import com.kapouter.pileapp.model.Plant

interface OnAddItemListener {
    fun onAddItem(item: Plant?)
}

class PlantAdapter(private val onAddListener: OnAddItemListener) :
    PagedListAdapter<Plant, PlantAdapter.ViewHolder>(PlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_plant, parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onAddListener)
    }

    class ViewHolder(private val binding: ItemPlantBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Plant?, onAddListener: OnAddItemListener) {
            with(binding) {
                plant = item
                addPlant.setOnClickListener(
                    if (plant?.isGrovePlant == 1) null
                    else View.OnClickListener { onAddListener.onAddItem(item) }
                )
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