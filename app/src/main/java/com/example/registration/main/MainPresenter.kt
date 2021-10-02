package com.example.registration.main

import com.example.registration.base.BasePresenter

class MainPresenter(private val name: String) : BasePresenter<MainView>() {
    fun onGreetingButtonClicked(){
        view?.showGreeting(name)
    }
}

