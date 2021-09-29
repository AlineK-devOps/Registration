package com.example.registration.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.registration.R
import com.example.registration.main.MainPresenter
import com.google.android.material.textfield.TextInputEditText
import android.widget.DatePicker

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import java.util.*


class RegistrationActivity : AppCompatActivity(), RegistrationView {

    private val presenter by lazy { RegistrationPresenter() }

    private lateinit var birthdayEt: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        presenter.attachView(this)

        title = resources.getText(R.string.registration_label)

        birthdayEt = findViewById(R.id.birthdayEt)

        birthdayEt.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus)
                callDatePicker()
        }
    }

    private fun callDatePicker() {
        val cal = Calendar.getInstance()

        val datePickerDialog = DatePickerDialog(this,
            { _, year, monthOfYear, dayOfMonth ->
                val editTextDateParam = dayOfMonth.toString() + "." + (monthOfYear + 1) + "." + year
                birthdayEt.setText(editTextDateParam)
            },
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}