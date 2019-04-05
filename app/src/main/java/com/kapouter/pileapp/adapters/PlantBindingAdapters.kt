package com.kapouter.pileapp.adapters

import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import com.kapouter.pileapp.R

@BindingAdapter("addOrCheck")
fun bindAddOrCheck(view: ImageView, isGrovePlant: Int?) {
    val isAdd = isGrovePlant == null || isGrovePlant == 0
    val src = if (isAdd) R.drawable.ic_add else R.drawable.ic_check
    val color = if (isAdd) R.color.colorPrimaryDark else R.color.green

    view.setImageResource(src)
    ImageViewCompat.setImageTintList(
        view,
        AppCompatResources.getColorStateList(view.context, color)
    )
}