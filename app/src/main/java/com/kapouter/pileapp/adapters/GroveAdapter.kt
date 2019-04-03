package com.kapouter.pileapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kapouter.pileapp.GroveFragmentDirections
import com.kapouter.pileapp.R
import com.kapouter.pileapp.databinding.ItemGroveBinding
import com.kapouter.pileapp.model.GrovePlant
import com.kapouter.pileapp.model.findFirstNonSvgUrl

class GroveAdapter :
    PagedListAdapter<GrovePlant, GroveAdapter.ViewHolder>(GrovePlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_grove, parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(private val binding: ItemGroveBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GrovePlant?) {
            with(binding) {
                binding.plant = item

                Glide.with(root)
                    .load(item?.images?.findFirstNonSvgUrl())
                    .centerCrop()
                    .placeholder(R.drawable.ic_flower)
                    .into(image)

                root.setOnClickListener {
                    if (item?.id != null)
                        root.findNavController().navigate(
                            GroveFragmentDirections.actionPlantListFragmentToPlantDetailFragment(item.id)
                        )
                }

                binding.executePendingBindings()
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