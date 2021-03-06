package com.example.registration.registration

import com.example.registration.base.BaseView
import java.util.*

interface RegistrationView : BaseView {
    fun changeError(error: String?, field: RegistrationField)
    fun openMainScreen(name: String)
    fun saveSession(name: String?, cacheSurname: String?, cacheBirthdate: String?, cachePassword: String?)
    fun setButtonEnabled()
    fun setButtonDisabled()
}