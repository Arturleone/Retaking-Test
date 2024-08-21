package com.example.modulo1refazer

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val menu = findViewById<ImageView>(R.id.menu)
        val textBemVindo = findViewById<TextView>(R.id.text_bem_vindo)

        val userName= intent.getStringExtra("username")
        textBemVindo.text = "Olá $userName!!"

        menu.setOnClickListener{
            AlertDialog.Builder(this).setMessage("Deseja realmente sair do aplicativo?").setPositiveButton("Sim") { _, _ ->
                finish()
            }.setNegativeButton("Não") {_, _ ->
                null
            }.show()
        }


    }
}