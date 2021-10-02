package com.example.registration.main

import com.example.registration.base.BaseView

interface MainView : BaseView {
    fun showGreeting(name: String)
}