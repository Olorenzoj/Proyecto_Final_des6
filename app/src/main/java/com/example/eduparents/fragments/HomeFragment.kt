package com.example.eduparents.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.eduparents.R
import com.example.eduparents.databinding.ActivityPanelGeneralBinding

class HomeFragment : Fragment(R.layout.activity_panel_general) {

    private var _binding: ActivityPanelGeneralBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ActivityPanelGeneralBinding.bind(view)

        // Recibir argumentos (nombre de usuario)
        val nombreUsuario = arguments?.getString("nombreUsuario") ?: "Padre"
        binding.welcomeText.text = "Bienvenido, $nombreUsuario"

        setupClickListeners(nombreUsuario)
    }

    private fun setupClickListeners(nombreUsuario: String) {
        // Botón: Ver Tareas
        binding.tasksButton.setOnClickListener {
            val bundle = Bundle().apply {
                putString("nombreUsuario", nombreUsuario)
            }
            findNavController().navigate(R.id.tasksFragment, bundle)
        }

        // Botón: Ver Calificaciones
        binding.gradesButton.setOnClickListener {
            // Navegar a fragment de calificaciones (crearlo primero)
            Toast.makeText(requireContext(), "Calificaciones próximamente", Toast.LENGTH_SHORT).show()
        }

        // Botón: Ver Eventos
        binding.eventsButton.setOnClickListener {
            Toast.makeText(requireContext(), "Eventos escolares próximamente", Toast.LENGTH_SHORT).show()
        }

        // Botón: Panel General (Dashboard)
        binding.dashboardButton.setOnClickListener {
            Toast.makeText(requireContext(), "Se confecciona un Dashboard resumen", Toast.LENGTH_SHORT).show()
        }

        // Botón: Perfil
        binding.profileButton.setOnClickListener {
            val bundle = Bundle().apply {
                putString("nombreUsuario", nombreUsuario)
            }
            findNavController().navigate(R.id.profileFragment, bundle)
        }

        // Botón: Cerrar sesión
        binding.logoutButton.setOnClickListener {
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}