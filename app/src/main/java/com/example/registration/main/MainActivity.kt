package com.example.registration.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.registration.R

class MainActivity : AppCompatActivity(), MainView {

    private val presenter by lazy { MainPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.attachView(this)
    }
}