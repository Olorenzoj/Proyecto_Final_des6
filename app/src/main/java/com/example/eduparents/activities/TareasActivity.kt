package com.example.eduparents.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eduparents.Tarea
import com.example.eduparents.TareasAdapter
import com.example.eduparents.databinding.ActivityTareasBinding

class TareasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTareasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTareasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listaTareas = listOf(
            Tarea("Tarea de Matemáticas", "Resolver los ejercicios de la página 45 del libro."),
            Tarea(
                "Proyecto de Ciencias",
                "Investigar sobre energías renovables y hacer una presentación."
            ),
            Tarea("Lectura de Español", "Leer el capítulo 3 y responder preguntas."),
            Tarea("Inglés", "Escribir una carta informal en inglés sobre tus vacaciones."),
            Tarea("Geografía", "Hacer un mapa de América del Sur con sus capitales.")
        )

        val adapter = TareasAdapter(listaTareas)
        binding.tareasRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.tareasRecyclerView.adapter = adapter
    }
}
