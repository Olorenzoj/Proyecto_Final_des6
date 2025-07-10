package com.example.eduparents.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eduparents.R
import com.example.eduparents.adapters.ScholarsEventsAdapter
import com.example.eduparents.databinding.FragmentScholarsEventsBinding

class ScholarsEventsFragment : Fragment(R.layout.fragment_scholars_events) {

    private var _binding: FragmentScholarsEventsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentScholarsEventsBinding.bind(view)

        val eventos = listOf(
            "Reunión de padres – 15 de julio",
            "Exposición de ciencias – 18 de julio",
            "Semana cultural – 22 al 26 de julio"
        )

        val adapter = ScholarsEventsAdapter(eventos)
        binding.eventsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.eventsRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
