package com.example.eduparents.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.eduparents.R
import com.example.eduparents.databinding.FragmentPerfilBinding

class ProfileFragment : Fragment(R.layout.fragment_perfil) {

    private var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPerfilBinding.bind(view)

        // Configurar datos del perfil (ejemplo)
        binding.nombreValueTextView.text = "Juan Pérez"
        binding.correoValueTextView.text = "juan.perez@example.com"
        binding.telefonoValueTextView.text = "+51 987 654 321"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}