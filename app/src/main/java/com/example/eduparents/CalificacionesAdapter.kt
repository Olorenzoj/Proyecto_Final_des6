package com.example.eduparents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eduparents.databinding.ItemCalificacionBinding

class CalificacionesAdapter(private val calificaciones: List<String>) :
    RecyclerView.Adapter<CalificacionesAdapter.CalificacionViewHolder>() {

    inner class CalificacionViewHolder(val binding: ItemCalificacionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalificacionViewHolder {
        val binding = ItemCalificacionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalificacionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalificacionViewHolder, position: Int) {
        val calificacion = calificaciones[position]
        holder.binding.calificacionText.text = calificacion
    }

    override fun getItemCount(): Int = calificaciones.size
}
