package com.kapouter.pileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.kapouter.pileapp.adapters.PlantAdapter
import com.kapouter.pileapp.viewmodels.PlantListViewModel
import kotlinx.android.synthetic.main.fragment_plant_list.view.*

class PlantListFragment : Fragment() {

    private lateinit var viewModel: PlantListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_plant_list, container, false)

        viewModel = ViewModelProviders.of(this).get(PlantListViewModel::class.java)
        val adapter = PlantAdapter()
        view.plants.adapter = adapter
        view.plants.layoutManager = LinearLayoutManager(context)
        viewModel.getPlants().observe(this, Observer { plants ->
            if (plants != null) adapter.submitList(plants)
        })

        return view
    }
}