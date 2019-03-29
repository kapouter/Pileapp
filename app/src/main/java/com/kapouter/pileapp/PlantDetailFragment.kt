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
import com.kapouter.pileapp.model.*
import com.kapouter.pileapp.viewmodels.PlantDetailViewModel
import kotlinx.android.synthetic.main.fragment_plant_detail.*
import kotlinx.android.synthetic.main.fragment_plant_detail.view.*
import kotlin.reflect.full.memberProperties

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
        setGrowthRate(plant.mainSpecies?.specifications?.growthRate)
        plant.mainSpecies?.specifications?.growthPeriod?.apply { setGrowthPeriod(this) }
        plant.mainSpecies?.seed?.bloomPeriod?.apply { setBloomPeriod(this) }
        plant.mainSpecies?.specifications?.toxicity?.apply { setToxicity(this) }
        plant.mainSpecies?.specifications?.leafRetention?.apply { setLeafRetention(this) }
        setPropagation(plant.mainSpecies?.propagation)
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

    private fun setGrowthRate(growthRate: Rate?) {
        if (growthRate == null)
            growthRateImage.setColorFilter(ContextCompat.getColor(context!!, R.color.grey), PorterDuff.Mode.SRC_IN)

        when (growthRate) {
            Rate.NONE ->
                growthRateImage.setColorFilter(ContextCompat.getColor(context!!, R.color.grey), PorterDuff.Mode.SRC_IN)
            Rate.SLOW -> growthRateImage.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.snail))
            Rate.MODERATE -> growthRateImage.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.horse))
            Rate.RAPID -> growthRateImage.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.cheetah))
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

    private fun setToxicity(toxicity: Toxicity) {
        when (toxicity) {
            Toxicity.NONE ->
                toxicityImage.setColorFilter(ContextCompat.getColor(context!!, R.color.grey), PorterDuff.Mode.SRC_IN)
            Toxicity.SLIGHT ->
                toxicityImage.setColorFilter(ContextCompat.getColor(context!!, R.color.yellow), PorterDuff.Mode.SRC_IN)
            Toxicity.MODERATE ->
                toxicityImage.setColorFilter(ContextCompat.getColor(context!!, R.color.orange), PorterDuff.Mode.SRC_IN)
            Toxicity.SEVERE ->
                toxicityImage.setColorFilter(ContextCompat.getColor(context!!, R.color.red), PorterDuff.Mode.SRC_IN)
        }
    }

    private fun setLeafRetention(leafRetention: Boolean) {
        if (leafRetention) leafRetentionImage.setColorFilter(
            ContextCompat.getColor(context!!, R.color.green),
            PorterDuff.Mode.SRC_IN
        )
    }

    private fun setPropagation(propagation: Propagation?) {
        if (propagation == null) propagationValue.text = getString(R.string.unknown)
        else {
            val propagationText = Propagation::class.memberProperties.filter {
                it.get(propagation) == true
            }.joinToString(", ") { it.name }
            if (propagationText.isNotEmpty())
                propagationValue.text = getString(R.string.by, propagationText)
            else
                propagationValue.text = getString(R.string.unknown)
        }
    }
}