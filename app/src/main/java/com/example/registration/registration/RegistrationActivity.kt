package com.example.registration.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.registration.R
import com.example.registration.main.MainPresenter

class RegistrationActivity : AppCompatActivity(), RegistrationView {

    private val presenter by lazy { RegistrationPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        presenter.attachView(this)
    }
}