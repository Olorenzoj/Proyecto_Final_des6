package com.example.eduparents.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eduparents.DBHelper
import com.example.eduparents.R
import com.example.eduparents.adapters.GradeAdapter

import com.example.eduparents.databinding.FragmentGradeBinding

class GradeFragment : Fragment(R.layout.fragment_grade) {

    private var _binding: FragmentGradeBinding? = null
    private val binding get() = _binding!!

    private lateinit var dbHelper: DBHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGradeBinding.bind(view)

        dbHelper = DBHelper(requireContext())

        // Recibe el nombre del usuario desde los argumentos (como se hace en HomeFragment)
        val nombreUsuario = arguments?.getString("nombreUsuario") ?: ""

        // Obtener calificaciones agrupadas por materia
        val calificacionesAgrupadas = dbHelper.obtenerNotasAgrupadasPorMateria(nombreUsuario)

        // Formatea la lista: materia, notas, promedio
        val listaFinal = calificacionesAgrupadas.map { (materia, notas) ->
            val promedio = notas.average()
            val detalleNotas = notas.joinToString(" / ") { String.format("%.2f", it) }
            "$materia:\n  Notas: $detalleNotas\n  Promedio: ${"%.2f".format(promedio)}"
        }

        val adapter = GradeAdapter(listaFinal)
        binding.gradeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.gradeRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
