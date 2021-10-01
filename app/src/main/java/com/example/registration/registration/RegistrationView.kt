package com.example.registration.registration

import com.example.registration.base.BaseView
import java.util.*

interface RegistrationView : BaseView {
    fun changeError(error: String?, field: RegistrationField)
    fun setButtonEnabled()
    fun setButtonDisabled()
}