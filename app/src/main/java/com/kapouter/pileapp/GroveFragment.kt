package com.kapouter.pileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.kapouter.pileapp.adapters.GroveAdapter
import com.kapouter.pileapp.viewmodels.GroveViewModel
import kotlinx.android.synthetic.main.fragment_grove.*
import kotlinx.android.synthetic.main.fragment_grove.view.*

class GroveFragment : Fragment() {

    private lateinit var viewModel: GroveViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_grove, container, false)

        val viewModelFactory = (activity as BaseActivity).viewModelFactory
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(GroveViewModel::class.java)

        val adapter = GroveAdapter()
        view.plants.adapter = adapter

        val layoutManager = LinearLayoutManager(context)
        view.plants.layoutManager = layoutManager
        val divider = DividerItemDecoration(context, layoutManager.orientation)
        val dividerRes = context?.let { AppCompatResources.getDrawable(it, R.drawable.divider) }
        dividerRes?.let { divider.setDrawable(it) }
        view.plants.addItemDecoration(divider)

        viewModel.getPlants().observe(this, Observer { plants -> if (plants != null) adapter.submitList(plants) })

        view.fab.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_plantListFragment_to_addPlantFragment))

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.postDelayed({ animateToKeyframeTwo() }, 500)
    }

    private fun animateToKeyframeTwo() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(context, R.layout.fragment_grove2)
        TransitionManager.beginDelayedTransition(constraintLayout)
        constraintSet.applyTo(constraintLayout)
    }
}