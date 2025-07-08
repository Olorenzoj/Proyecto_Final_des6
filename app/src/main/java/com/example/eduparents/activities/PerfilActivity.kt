package com.example.eduparents.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eduparents.DBHelper
import com.example.eduparents.databinding.FragmentPerfilBinding

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding: FragmentPerfilBinding
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentPerfilBinding.inflate(layoutInflater)
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

            binding.nombreValueTextView.text = nombre
            binding.correoValueTextView.text = correo
        }

        cursor.close()
    }
}
