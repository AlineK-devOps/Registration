package com.example.registration.utils

import java.lang.Exception
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Validator {
    fun checkName(name: String): String{
        return when (true){
            name.length < 2 ->  "Имя не может содержать менее двух символов"
            Regex("[а-яa-z]*").matches(name.lowercase()) -> "valid"
            else -> "Имя должно содержать только буквы"
        }
    }

    fun checkSurname(surname: String): String{
        return when (true){
            surname.length < 2 ->  "Фамилия не может содержать менее двух символов"
            Regex("[а-яa-z]*").matches(surname.lowercase()) -> "valid"
            else -> "Фамилия должна содержать только буквы"
        }
    }

    fun checkDate(birthDate: String): String{
        if (birthDate.isEmpty()){
            return "Дата не задана"
        }
        else{
            try{
                val sdf: DateFormat = SimpleDateFormat("d.M.y", Locale.ENGLISH)
                sdf.isLenient = false

                val date = sdf.parse(birthDate)

                if (Regex("[0-9.]*").matches(birthDate) && date != null && date.year + 1900 >= 1900 && date.before(Date()))
                    return "valid"
            }
            catch (ex: Exception){}
        }
        return "Неккоректная дата"
    }

    fun checkPassword(password: String): String{
        return when (true){
            password.length < 8 ->  "Пароль не может содержать менее восьми символов"
            !Regex("[0-9]+"). containsMatchIn(password) -> "Пароль должен содержать хотя бы одну цифру"
            !Regex("[а-яa-z]+"). containsMatchIn(password) -> "Пароль должен содержать хотя бы одну букву в нижнем регистре"
            !Regex("[А-ЯA-Z]+"). containsMatchIn(password) -> "Пароль должен содержать хотя бы одну букву в верхнем регистре"
            else -> "valid"
        }
    }

    fun checkPasswordConfirm(passwordConfirm: String, password: String?): String{
        return if (password == passwordConfirm)
            "valid"
        else
            "Пароли не совпадают"
    }
}