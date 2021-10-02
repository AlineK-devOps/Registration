package com.example.registration.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.registration.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity(), MainView {

    companion object{
        private const val EXTRA_NAME = "EXTRA_NAME"

        fun start(context: Context, name: String){
            val intent = Intent(context, MainActivity::class.java).apply {
                putExtra(EXTRA_NAME, name)
            }
            context.startActivity(intent)
        }
    }

    private val presenter by lazy {
        intent.getStringExtra(EXTRA_NAME)?.let {
        MainPresenter(it) }
    }

    private lateinit var greetingButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bind()
    }

    override fun showGreeting(name: String){
        MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.hello_dialog) + " " + name + "!")
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

    private fun bind(){
        presenter?.attachView(this)
        title = "Главный экран"

        greetingButton = findViewById(R.id.greetingButton)
        greetingButton.setOnClickListener {
            presenter?.onGreetingButtonClicked()
        }
    }
}