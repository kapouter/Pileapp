package com.kapouter.pileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.kapouter.pileapp.model.GrovePlant
import com.kapouter.pileapp.model.findFirstNonSvgUrl
import com.kapouter.pileapp.viewmodels.PlantDetailViewModel
import com.kapouter.pileapp.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_plant_detail.*

class PlantDetailFragment : Fragment() {

    private lateinit var viewModel: PlantDetailViewModel
    private val args: PlantDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this, ViewModelFactory(activity!!.applicationContext, args.plantId))
            .get(PlantDetailViewModel::class.java)
        viewModel.getPlant().observe(this, Observer {
            if (it != null) updateView(it)
        })

        return inflater.inflate(R.layout.fragment_plant_detail, container, false)
    }

    private fun updateView(plant: GrovePlant) {
        Glide.with(view!!)
            .load(plant.images?.findFirstNonSvgUrl())
            .centerCrop()
            .placeholder(R.drawable.ic_flower)
            .into(image)

        name.text = plant.name ?: plant.scientificName
        scientificName.text = plant.scientificName
    }
}