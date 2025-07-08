package com.example.eduparents.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.eduparents.DBHelper
import com.example.eduparents.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)

        binding.loginButton.setOnClickListener {
            val usuario = binding.usernameEditText.text.toString().trim()
            val contrasena = binding.passwordEditText.text.toString().trim()

            if (usuario.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Por favor completa ambos campos", Toast.LENGTH_SHORT).show()
            } else {
                val valido = dbHelper.verificarCredenciales(usuario, contrasena)
                if (valido) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("nombreUsuario", usuario)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            }
        }
        // Botón de ir al registro
        binding.registerText.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
