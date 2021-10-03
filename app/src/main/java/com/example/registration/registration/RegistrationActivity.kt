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
import android.content.SharedPreferences
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import com.example.registration.data.User
import com.example.registration.main.MainActivity
import com.example.registration.utils.Validator
import com.google.android.material.textfield.TextInputLayout
import java.util.*


class RegistrationActivity : AppCompatActivity(), RegistrationView {

    companion object{
        var APP_PREFERENCES = "user"
    }

    private val presenter by lazy { RegistrationPresenter(User()) }

    private lateinit var prefs: SharedPreferences

    private lateinit var registrationButton: Button

    private lateinit var nameEt: TextInputEditText
    private lateinit var surnameEt: TextInputEditText
    private lateinit var passwordEt: TextInputEditText
    private lateinit var passwordConfirmEt: TextInputEditText
    private lateinit var birthDateEt: TextInputEditText

    private lateinit var nameTextInputLayout: TextInputLayout
    private lateinit var surnameTextInputLayout: TextInputLayout
    private lateinit var birthDateTextInputLayout: TextInputLayout
    private lateinit var passwordTextInputLayout: TextInputLayout
    private lateinit var passwordConfirmTextInputLayout: TextInputLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        bind()

        if (prefs.contains("name")){
            val name = prefs.getString("name", "none")
            name?.let { openMainScreen(it) }
        }

        if (savedInstanceState != null){
            nameEt.setText(savedInstanceState.getString("name"))
            surnameEt.setText(savedInstanceState.getString("surname"))
            birthDateEt.setText(savedInstanceState.getString("birthdate"))
            passwordEt.setText(savedInstanceState.getString("password"))
            passwordConfirmEt.setText(savedInstanceState.getString("passwordConfirm"))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("name", nameEt.text.toString())
        outState.putString("surname", surnameEt.text.toString())
        outState.putString("birthdate", birthDateEt.text.toString())
        outState.putString("password", passwordEt.text.toString())
        outState.putString("passwordConfirm", passwordConfirmEt.text.toString())
    }

    override fun changeError(error: String?, field: RegistrationField){
        when (field) {
            RegistrationField.NAME -> nameTextInputLayout.error = error
            RegistrationField.SURNAME -> surnameTextInputLayout.error = error
            RegistrationField.BIRTHDATE -> birthDateTextInputLayout.error = error
            RegistrationField.PASSWORD -> passwordTextInputLayout.error = error
            RegistrationField.PASSWORD_CONFIRM -> passwordConfirmTextInputLayout.error = error
        }
    }

    override fun saveSession(name: String?, cacheSurname: String?, cacheBirthdate: String?, cachePassword: String?) {
        val editor = prefs.edit()

        editor.putString("name", name)
        editor.putString("surname", cacheSurname)
        editor.putString("birthdate", cacheBirthdate)
        editor.putString("password", cachePassword)

        editor.apply()
    }

    override fun setButtonDisabled() {
        registrationButton.isEnabled = false
    }

    override fun setButtonEnabled() {
        registrationButton.isEnabled = true
    }

    override fun openMainScreen(name: String){
        MainActivity.start(this, name)
    }

    private fun bind(){
        presenter.attachView(this)

        prefs = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)

        title = resources.getText(R.string.registration_label)

        registrationButton = findViewById(R.id.registrationButton)
        nameTextInputLayout = findViewById(R.id.nameTextInputLayout)
        surnameTextInputLayout = findViewById(R.id.surnameTextInputLayout)
        birthDateTextInputLayout = findViewById(R.id.birthdayTextInputLayout)
        passwordTextInputLayout = findViewById(R.id.passwordTextInputLayout)
        passwordConfirmTextInputLayout = findViewById(R.id.passwordConfirmTextInputLayout)

        nameEt = findViewById(R.id.nameEt)
        nameEt.addTextChangedListener {
            presenter.checkField(nameEt.text.toString(), RegistrationField.NAME)
        }

        surnameEt = findViewById(R.id.surnameEt)
        surnameEt.addTextChangedListener {
            presenter.checkField(surnameEt.text.toString(), RegistrationField.SURNAME)
        }

        passwordEt = findViewById(R.id.passwordEt)
        passwordEt.addTextChangedListener {
            presenter.checkField(passwordEt.text.toString(), RegistrationField.PASSWORD)
        }

        passwordConfirmEt = findViewById(R.id.passwordConfirmEt)
        passwordConfirmEt.addTextChangedListener {
            presenter.checkField(passwordConfirmEt.text.toString(), RegistrationField.PASSWORD_CONFIRM)
        }

        birthDateEt = findViewById(R.id.birthdateEt)
        birthDateEt.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus)
                callDatePicker()
        }
        birthDateEt.addTextChangedListener {
            presenter.checkField(birthDateEt.text.toString(), RegistrationField.BIRTHDATE)
        }

        registrationButton.setOnClickListener {
            presenter.onRegistrationButtonClicked()
        }
    }

    private fun callDatePicker() {
        val calendar = presenter.getCurrentDate()

        val datePickerDialog = DatePickerDialog(this,
            { _, year, monthOfYear, dayOfMonth ->
                val editTextDateParam = dayOfMonth.toString() + "." + (monthOfYear + 1) + "." + year
                birthDateEt.setText(editTextDateParam)
            },
            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}