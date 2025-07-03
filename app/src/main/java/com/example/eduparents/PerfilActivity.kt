package com.example.eduparents

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eduparents.databinding.ActivityPerfilBinding

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val usuario = intent.getStringExtra("nombreUsuario") ?: ""
        dbHelper = DBHelper(this)

        // Buscar datos del usuario
        val cursor = dbHelper.readableDatabase.rawQuery(
            "SELECT * FROM usuarios WHERE usuario = ?",
            arrayOf(usuario)
        )

        if (cursor.moveToFirst()) {
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
            val correo = cursor.getString(cursor.getColumnIndexOrThrow("correo"))
            val username = cursor.getString(cursor.getColumnIndexOrThrow("usuario"))

            binding.nombreTextView.text = nombre
            binding.correoTextView.text = correo
            binding.usuarioTextView.text = username
        }

        cursor.close()
    }
}
