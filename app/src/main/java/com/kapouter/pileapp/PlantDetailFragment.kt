package com.kapouter.pileapp

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.kapouter.pileapp.adapters.PlantImageAdapter
import com.kapouter.pileapp.databinding.FragmentPlantDetailBinding
import com.kapouter.pileapp.model.BloomPeriod
import com.kapouter.pileapp.model.GrovePlant
import com.kapouter.pileapp.model.GrowthPeriod
import com.kapouter.pileapp.model.Lifespan
import com.kapouter.pileapp.viewmodels.PlantDetailViewModel
import kotlinx.android.synthetic.main.fragment_plant_detail.*

class PlantDetailFragment : Fragment() {

    private lateinit var plantDetailViewModel: PlantDetailViewModel
    private val args: PlantDetailFragmentArgs by navArgs()

    private lateinit var imageAdapter: PlantImageAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModelFactory = (activity as BaseActivity).viewModelFactory
        plantDetailViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(PlantDetailViewModel::class.java)

        imageAdapter = PlantImageAdapter(context!!)

        val binding = DataBindingUtil.inflate<FragmentPlantDetailBinding>(
            inflater, R.layout.fragment_plant_detail, container, false
        )
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = plantDetailViewModel

            imagePager.adapter = imageAdapter
            remove.setOnClickListener {
                plantDetailViewModel.removePlant()
                Navigation.findNavController(root).popBackStack()
            }
        }

        plantDetailViewModel.getPlant().observe(this, Observer {
            if (it != null) updateView(it)
        })
        plantDetailViewModel.loadPlant(args.plantId)

        return binding.root
    }

    private fun updateView(plant: GrovePlant) {
        imageAdapter.images = plant.images ?: listOf()

        plant.mainSpecies?.specifications?.lifespan?.apply { setLifespan(this) }
        plant.mainSpecies?.specifications?.growthPeriod?.apply { setGrowthPeriod(this) }
        plant.mainSpecies?.seed?.bloomPeriod?.apply { setBloomPeriod(this) }
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

    private fun setGrowthPeriod(growthPeriod: GrowthPeriod) {
        when (growthPeriod) {
            GrowthPeriod.SPRING ->
                spring.setColorFilter(ContextCompat.getColor(context!!, R.color.green), PorterDuff.Mode.SRC_IN)
            GrowthPeriod.FALL ->
                fall.setColorFilter(ContextCompat.getColor(context!!, R.color.orange), PorterDuff.Mode.SRC_IN)
            GrowthPeriod.SUMMER ->
                summer.setColorFilter(ContextCompat.getColor(context!!, R.color.yellow), PorterDuff.Mode.SRC_IN)
            GrowthPeriod.SPRING_FALL -> {
                spring.setColorFilter(ContextCompat.getColor(context!!, R.color.green), PorterDuff.Mode.SRC_IN)
                fall.setColorFilter(ContextCompat.getColor(context!!, R.color.orange), PorterDuff.Mode.SRC_IN)
            }
            GrowthPeriod.SPRING_SUMMER_FALL -> {
                spring.setColorFilter(ContextCompat.getColor(context!!, R.color.green), PorterDuff.Mode.SRC_IN)
                summer.setColorFilter(ContextCompat.getColor(context!!, R.color.yellow), PorterDuff.Mode.SRC_IN)
                fall.setColorFilter(ContextCompat.getColor(context!!, R.color.orange), PorterDuff.Mode.SRC_IN)
            }
            GrowthPeriod.SUMMER_FALL -> {
                summer.setColorFilter(ContextCompat.getColor(context!!, R.color.yellow), PorterDuff.Mode.SRC_IN)
                fall.setColorFilter(ContextCompat.getColor(context!!, R.color.orange), PorterDuff.Mode.SRC_IN)
            }
            GrowthPeriod.FALL_WINTER_SPRING -> {
                fall.setColorFilter(ContextCompat.getColor(context!!, R.color.orange), PorterDuff.Mode.SRC_IN)
                winter.setColorFilter(ContextCompat.getColor(context!!, R.color.blue_light), PorterDuff.Mode.SRC_IN)
                spring.setColorFilter(ContextCompat.getColor(context!!, R.color.green), PorterDuff.Mode.SRC_IN)
            }
            GrowthPeriod.SPRING_SUMMER -> {
                spring.setColorFilter(ContextCompat.getColor(context!!, R.color.green), PorterDuff.Mode.SRC_IN)
                summer.setColorFilter(ContextCompat.getColor(context!!, R.color.yellow), PorterDuff.Mode.SRC_IN)
            }
            GrowthPeriod.YEAR_ROUND -> {
                spring.setColorFilter(ContextCompat.getColor(context!!, R.color.green), PorterDuff.Mode.SRC_IN)
                summer.setColorFilter(ContextCompat.getColor(context!!, R.color.yellow), PorterDuff.Mode.SRC_IN)
                fall.setColorFilter(ContextCompat.getColor(context!!, R.color.orange), PorterDuff.Mode.SRC_IN)
                winter.setColorFilter(ContextCompat.getColor(context!!, R.color.blue_light), PorterDuff.Mode.SRC_IN)
            }
        }
    }

    private fun setBloomPeriod(bloomPeriod: BloomPeriod) {
        when (bloomPeriod) {
            BloomPeriod.SPRING, BloomPeriod.EARLY_SPRING, BloomPeriod.MID_SPRING, BloomPeriod.LATE_SPRING ->
                springb.setColorFilter(ContextCompat.getColor(context!!, R.color.green), PorterDuff.Mode.SRC_IN)
            BloomPeriod.SUMMER, BloomPeriod.EARLY_SUMMER, BloomPeriod.MID_SUMMER, BloomPeriod.LATE_SUMMER ->
                summerb.setColorFilter(ContextCompat.getColor(context!!, R.color.yellow), PorterDuff.Mode.SRC_IN)
            BloomPeriod.FALL ->
                fallb.setColorFilter(ContextCompat.getColor(context!!, R.color.orange), PorterDuff.Mode.SRC_IN)
            BloomPeriod.WINTER, BloomPeriod.LATE_WINTER ->
                winterb.setColorFilter(ContextCompat.getColor(context!!, R.color.blue_light), PorterDuff.Mode.SRC_IN)
            BloomPeriod.INDETERMINATE -> return
        }
    }
}