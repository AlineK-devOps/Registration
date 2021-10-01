package com.example.registration.data

data class User(
    var name: String? = null,
    var surname: String? = null,
    var birthdate: String? = null,
    var password: String? = null,
    var passwordConfirm: String? = null
){
    fun isFieldsValid(): Boolean{
        return name != null && surname != null && birthdate != null && password != null && passwordConfirm != null
    }
}