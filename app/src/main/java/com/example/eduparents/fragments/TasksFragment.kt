package com.example.eduparents.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eduparents.Tarea
import com.example.eduparents.TareasAdapter
import com.example.eduparents.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listaTareas = listOf(
            Tarea("Tarea de Matemáticas", "Resolver los ejercicios de la página 45 del libro."),
            Tarea("Proyecto de Ciencias", "Investigar sobre energías renovables y hacer una presentación."),
            Tarea("Lectura de Español", "Leer el capítulo 3 y responder preguntas."),
            Tarea("Inglés", "Escribir una carta informal en inglés sobre tus vacaciones."),
            Tarea("Geografía", "Hacer un mapa de América del Sur con sus capitales.")
        )

        val adapter = TareasAdapter(listaTareas)
        binding.tareasRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.tareasRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
