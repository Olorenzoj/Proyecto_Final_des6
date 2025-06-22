package com.example.eduparents

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.eduparents.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)

        binding.registerButton.setOnClickListener {
            val nombre = binding.nameEditText.text.toString()
            val correo = binding.emailEditText.text.toString()
            val usuario = binding.usernameEditText.text.toString()
            val pass = binding.passwordEditText.text.toString()

            if (nombre.isEmpty() || correo.isEmpty() || usuario.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val exito = dbHelper.registrarUsuario(nombre, correo, usuario, pass)
                if (exito) {
                    Toast.makeText(this, "Padre registrado exitosamente", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "Ese nombre de usuario ya existe", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
