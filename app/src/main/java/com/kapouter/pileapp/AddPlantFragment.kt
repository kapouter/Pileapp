package com.kapouter.pileapp

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kapouter.pileapp.adapters.OnAddItemListener
import com.kapouter.pileapp.adapters.PlantAdapter
import com.kapouter.pileapp.model.Plant
import com.kapouter.pileapp.viewmodels.AddPlantViewModel
import com.kapouter.pileapp.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_add_plant.view.*

class AddPlantFragment : Fragment() {

    private lateinit var viewModel: AddPlantViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_plant, container, false)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(activity!!.applicationContext))
            .get(AddPlantViewModel::class.java)
        val adapter = PlantAdapter(object : OnAddItemListener {
            override fun onAddItem(item: Plant?) {
                if (item != null) viewModel.addPlant(item.id)
            }
        })
        view.plants.adapter = adapter

        val layoutManager = LinearLayoutManager(context)
        view.plants.layoutManager = layoutManager
        val divider = DividerItemDecoration(context, layoutManager.orientation)
        val dividerRes = context?.let { AppCompatResources.getDrawable(it, R.drawable.divider) }
        dividerRes?.let { divider.setDrawable(it) }
        view.plants.addItemDecoration(divider)

        viewModel.getPlants().observe(this, Observer { plants -> if (plants != null) adapter.submitList(plants) })
        viewModel.getError()
            .observe(this, Observer {
                if (it.isNotEmpty()) Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })

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
                    viewModel.searchPlants(newText ?: "")
                    return false
                }
            })
        }

        super.onCreateOptionsMenu(menu, inflater)
    }
}