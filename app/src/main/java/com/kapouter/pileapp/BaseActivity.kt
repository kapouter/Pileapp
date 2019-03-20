package com.kapouter.pileapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kapouter.pileapp.viewmodels.ViewModelFactory
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this.application as Pileapp).appComponent.inject(this)
    }
}