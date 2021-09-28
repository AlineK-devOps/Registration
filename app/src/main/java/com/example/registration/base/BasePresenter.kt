package com.example.registration.base

open class BasePresenter <View : BaseView> {
    protected var view: View? = null

    fun attachView(view: View){
        this.view = view
    }

    fun destroy(){
        this.view = null
    }
}