package com.kapouter.pileapp.adapters

import android.graphics.PorterDuff
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.kapouter.pileapp.R
import com.kapouter.pileapp.model.Propagation
import com.kapouter.pileapp.model.Rate
import com.kapouter.pileapp.model.Toxicity
import kotlin.reflect.full.memberProperties

@BindingAdapter("growthRate")
fun bindGrowthRate(view: ImageView, growthRate: Rate?) {
    val src = when (growthRate) {
        Rate.NONE, Rate.SLOW -> R.drawable.snail
        Rate.MODERATE -> R.drawable.horse
        Rate.RAPID -> R.drawable.cheetah
        else -> R.drawable.snail
    }
    view.setImageDrawable(ContextCompat.getDrawable(view.context!!, src))

    if (growthRate == null || growthRate == Rate.NONE) {
        view.setColorFilter(ContextCompat.getColor(view.context!!, R.color.grey), PorterDuff.Mode.SRC_IN)
    } else {
        view.colorFilter = null
    }
}

@BindingAdapter("toxicity")
fun bindToxicity(view: ImageView, toxicity: Toxicity?) {
    val color = when (toxicity) {
        Toxicity.NONE -> R.color.grey
        Toxicity.SLIGHT -> R.color.yellow
        Toxicity.MODERATE -> R.color.orange
        Toxicity.SEVERE -> R.color.red
        else -> R.color.grey
    }
    view.setColorFilter(ContextCompat.getColor(view.context!!, color), PorterDuff.Mode.SRC_IN)
}

@BindingAdapter("leafRetention")
fun bindLeafRetention(view: ImageView, leafRetention: Boolean?) {
    val color = if (leafRetention != null && leafRetention) R.color.green else R.color.grey
    view.setColorFilter(
        ContextCompat.getColor(view.context!!, color),
        PorterDuff.Mode.SRC_IN
    )
}

@BindingAdapter("propagationValue")
fun bindPropagationValue(view: TextView, propagation: Propagation?) {
    val resources = view.context.resources

    if (propagation == null) view.text = resources.getString(R.string.unknown)
    else {
        val propagationText = Propagation::class.memberProperties.filter {
            it.get(propagation) == true
        }.joinToString(", ") { it.name }
        if (propagationText.isNotEmpty())
            view.text = resources.getString(R.string.by, propagationText)
        else
            view.text = resources.getString(R.string.unknown)
    }
}