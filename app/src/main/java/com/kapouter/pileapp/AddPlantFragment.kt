package com.kapouter.pileapp

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.kapouter.pileapp.adapters.OnAddItemListener
import com.kapouter.pileapp.adapters.PlantAdapter
import com.kapouter.pileapp.data.Plant
import com.kapouter.pileapp.viewmodels.AddPlantViewModel
import kotlinx.android.synthetic.main.fragment_add_plant.view.*

class AddPlantFragment : Fragment() {

    private lateinit var viewModel: AddPlantViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_plant, container, false)

        viewModel = ViewModelProviders.of(this).get(AddPlantViewModel::class.java)
        val adapter = PlantAdapter(object : OnAddItemListener {
            override fun onAddItem(item: Plant) {
                viewModel.addPlant(item)
            }
        })
        view.plants.adapter = adapter
        view.plants.layoutManager = LinearLayoutManager(context)
        viewModel.getPlants().observe(this, Observer { plants -> if (plants != null) adapter.submitList(plants) })

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add_plant, menu)

        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.loadPlants(newText ?: "")
                    return false
                }
            })
        }
    }


}