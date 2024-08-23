package com.example.modulo1refazer

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Cadastro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)

        val nameFull = findViewById<EditText>(R.id.nome_completo_cadastro)
        val nickName = findViewById<EditText>(R.id.nome_usuario_cadastro)
        val password = findViewById<EditText>(R.id.senha_cadastro)
        val confirmPassword = findViewById<EditText>(R.id.confirmar_senha_cadastro)
        val buttonCadastrarCadastro = findViewById<Button>(R.id.botao_cadastrar_cadastro)
        val iconVoltar = findViewById<ImageView>(R.id.icon_voltar)
        iconVoltar.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }

        buttonCadastrarCadastro.setOnClickListener {
            val inputNameFull = nameFull.text.toString()
            val inputNickName = nickName.text.toString()
            val inputPassword = password.text.toString()
            val inputConfirmPassword = confirmPassword.text.toString()

            if (inputNameFull.isBlank() || inputNickName.isBlank() || inputPassword.isBlank() || inputConfirmPassword.isBlank()) {
                messageShowBlock("Preencha Todos Os Dados!")
            } else if (!verificarNomeCompleto(inputNameFull)) {
                messageShowBlock("O Nome Completo deve conter pelo menos dois nomes!")
            } else if (!verificarPassword(inputPassword)) {
                messageShowBlock("É necessário no mínimo 6 caracteres, no mínimo dois números e duas letras.\n")
            } else if (compararPassword(inputPassword, inputConfirmPassword)) {
                messageShowBlock("As Senhas Não Conferem")
            } else if (!verificarUser(inputNickName)) {
                messageShowBlock("Usuário já existente!")
            } else {
                adicionarUser(inputNickName, inputPassword)
                messageShowBlock("Cadastro Realizado Com Sucesso!")
                startActivity(Intent(this, Login::class.java))
            }
        }
    }

    private fun messageShowBlock(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun verificarPassword(password: String): Boolean {
        if (password.length < 6) return false
        if (password.count { it.isDigit() } < 2) return false
        if (password.count { it.isLetter() } < 2) return false
        return true
    }

    private fun verificarNomeCompleto(nomeCompleto: String): Boolean {
        val palavras = nomeCompleto.trim().split("\\s+".toRegex())
        return palavras.size >= 2
    }

    private fun compararPassword(password: String, confirmPassword: String): Boolean {
        return password != confirmPassword
    }

    private fun adicionarUser(userName: String, password: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE)
        val userApp = sharedPreferences.edit()
        userApp.putString("username", userName)
        userApp.putString("password", password)
        userApp.apply()
    }

    private fun verificarUser(userName: String): Boolean {
        val sharedPreferences: SharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE)
        return userName != sharedPreferences.getString("username", null)
    }
}
