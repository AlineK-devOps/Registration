package com.example.registration.registration

import com.example.registration.base.BasePresenter
import com.example.registration.data.User
import com.example.registration.utils.Validator
import java.util.*

class RegistrationPresenter(private val user: User) : BasePresenter<RegistrationView>() {

    fun checkField(value: String, field: RegistrationField){
        var error: String?

        when (field){
            RegistrationField.NAME ->{
                error = Validator.checkName(value)

                if (error == "valid"){
                    user.name = value
                    error = null
                }
                else user.name = null

                view?.changeError(error, RegistrationField.NAME)
            }

            RegistrationField.SURNAME ->{
                error = Validator.checkSurname(value)

                if (error == "valid"){
                    user.surname = value
                    error = null
                }
                else user.surname = null

                view?.changeError(error, RegistrationField.SURNAME)
            }

            RegistrationField.BIRTHDATE ->{
                error = Validator.checkDate(value)

                if (error == "valid"){
                    user.birthdate = value
                    error = null
                }
                else user.birthdate = null

                view?.changeError(error, RegistrationField.BIRTHDATE)
            }

            RegistrationField.PASSWORD ->{
                error = Validator.checkPassword(value)

                if (error == "valid"){
                    user.password = value
                    error = null
                }
                else user.password = null

                view?.changeError(error, RegistrationField.PASSWORD)
            }

            RegistrationField.PASSWORD_CONFIRM ->{
                error = Validator.checkPasswordConfirm(value, user.password)

                if (error == "valid"){
                    user.passwordConfirm = value
                    error = null
                }
                else user.passwordConfirm = null

                view?.changeError(error, RegistrationField.PASSWORD_CONFIRM)
            }
        }
        checkButtonEnable()
    }

    fun onRegistrationButtonClicked(){
        view?.saveSession(user.name, user.surname, user.birthdate, user.password)
        user.name?.let { view?.openMainScreen(it) }
    }

    fun getCurrentDate(): Calendar {
        return Calendar.getInstance()
    }

    private fun checkButtonEnable(){
        if (user.isFieldsValid())
            view?.setButtonEnabled()
        else
            view?.setButtonDisabled()
    }
}