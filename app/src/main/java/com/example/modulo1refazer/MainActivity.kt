package com.example.modulo1refazer

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {

        private lateinit var carousel: ViewPager
        private val handler = Handler(Looper.getMainLooper())
        private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menu = findViewById<ImageView>(R.id.menu)
        val textBemVindo = findViewById<TextView>(R.id.text_bem_vindo)
        val worldSkills = findViewById<Button>(R.id.worldSkills)
        val contato = findViewById<Button>(R.id.Contato)
        val menuInflar = findViewById<Button>(R.id.Menu)


        val userName = intent.getStringExtra("username")
        textBemVindo.text = "Olá $userName!!"

        menu.setOnClickListener {
            AlertDialog.Builder(this).setMessage("Deseja realmente sair do aplicativo?")
                .setPositiveButton("Sim") { _, _ ->
                    finishAffinity()
                }
                .setNegativeButton("Não") { _, _ ->
                    // O que fazer se o usuário escolher "Não"
                }
                .show()
        }

        setupCarousel()
        startAutoScroll()

        worldSkills.setOnClickListener {
            val layoutInterno = findViewById<ViewGroup>(R.id.layoutInterno)
            layoutInterno.removeAllViews() // Remove todos os filhos antes de inflar o novo layout
            layoutInflater.inflate(R.layout.worldskills, layoutInterno, true)
        }

        contato.setOnClickListener {
            val layoutInterno = findViewById<ViewGroup>(R.id.layoutInterno)
            layoutInterno.removeAllViews() // Remove todos os filhos antes de inflar o novo layout
            layoutInflater.inflate(R.layout.contato, layoutInterno, true)
        }

        menuInflar.setOnClickListener {
            val layoutInterno = findViewById<ViewGroup>(R.id.layoutInterno)
            layoutInterno.removeAllViews() // Remove todos os filhos antes de inflar o novo layout
            layoutInflater.inflate(R.layout.home, layoutInterno, true)
        }
    }
    private fun setupCarousel() {
        carousel = findViewById(R.id.carousel)
        val images = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3)
        carousel.adapter = CarouselAdapter(images)
    }

    // Inicia a rotação automática do carrossel
    private fun startAutoScroll() {
        val update = Runnable {
            if (currentPage == (carousel.adapter?.count ?: 1) - 1) {
                currentPage = 0
            } else {
                currentPage++
            }
            carousel.setCurrentItem(currentPage, true)
        }

        handler.postDelayed(object : Runnable {
            override fun run() {
                update.run()
                handler.postDelayed(this, 3000)
            }
        }, 3000)
    }
}

