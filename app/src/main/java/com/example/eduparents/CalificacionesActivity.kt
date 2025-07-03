package com.example.eduparents

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eduparents.databinding.ActivityCalificacionesBinding

class CalificacionesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalificacionesBinding
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalificacionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)

        val usuario = intent.getStringExtra("nombreUsuario") ?: ""

        // Obtener notas agrupadas por materia
        val calificacionesAgrupadas = dbHelper.obtenerNotasAgrupadasPorMateria(usuario)

        // Formatear la lista para mostrar materias, notas y promedio
        val listaFinal = calificacionesAgrupadas.map { (materia, notas) ->
            val promedio = notas.average()
            val detalleNotas = notas.joinToString(" / ") { String.format("%.2f", it) }
            "$materia:\n  Notas: $detalleNotas\n  Promedio: ${"%.2f".format(promedio)}"
        }

        val adapter = CalificacionesAdapter(listaFinal)
        binding.calificacionesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.calificacionesRecyclerView.adapter = adapter
    }
}
