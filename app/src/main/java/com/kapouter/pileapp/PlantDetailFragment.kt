package com.kapouter.pileapp

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.kapouter.pileapp.adapters.PlantImageAdapter
import com.kapouter.pileapp.model.GrovePlant
import com.kapouter.pileapp.model.Lifespan
import com.kapouter.pileapp.viewmodels.PlantDetailViewModel
import kotlinx.android.synthetic.main.fragment_plant_detail.*
import kotlinx.android.synthetic.main.fragment_plant_detail.view.*

class PlantDetailFragment : Fragment() {

    private lateinit var viewModel: PlantDetailViewModel
    private val args: PlantDetailFragmentArgs by navArgs()

    private lateinit var imageAdapter: PlantImageAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_plant_detail, container, false)

        imageAdapter = PlantImageAdapter(context!!)
        view.imagePager.adapter = imageAdapter

        val viewModelFactory = (activity as BaseActivity).viewModelFactory
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(PlantDetailViewModel::class.java)
        viewModel.getPlant().observe(this, Observer {
            if (it != null) updateView(it)
        })
        viewModel.loadPlant(args.plantId)

        return view
    }

    private fun updateView(plant: GrovePlant) {
        imageAdapter.images = plant.images ?: listOf()
        name.text = plant.name ?: plant.scientificName
        scientificName.text = plant.scientificName

        plant.mainSpecies?.specifications?.lifespan?.apply { setLifespan(this) }
    }

    private fun setLifespan(lifespan: Lifespan) {
        when (lifespan) {
            Lifespan.SHORT ->
                lifespan1.setColorFilter(ContextCompat.getColor(context!!, R.color.green), PorterDuff.Mode.SRC_IN)
            Lifespan.MODERATE -> {
                lifespan1.setColorFilter(ContextCompat.getColor(context!!, R.color.green), PorterDuff.Mode.SRC_IN)
                lifespan2.setColorFilter(ContextCompat.getColor(context!!, R.color.green), PorterDuff.Mode.SRC_IN)
            }
            Lifespan.LONG -> {
                lifespan1.setColorFilter(ContextCompat.getColor(context!!, R.color.green), PorterDuff.Mode.SRC_IN)
                lifespan2.setColorFilter(ContextCompat.getColor(context!!, R.color.green), PorterDuff.Mode.SRC_IN)
                lifespan3.setColorFilter(ContextCompat.getColor(context!!, R.color.green), PorterDuff.Mode.SRC_IN)
            }
        }
    }
}