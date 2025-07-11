package com.example.eduparents.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eduparents.databinding.ItemCalificacionBinding

class GradeAdapter(private val calificaciones: List<String>) :
    RecyclerView.Adapter<GradeAdapter.GradeViewHolder>() {

    inner class GradeViewHolder(val binding: ItemCalificacionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeViewHolder {
        val binding = ItemCalificacionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GradeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GradeViewHolder, position: Int) {
        val calificacion = calificaciones[position]
        holder.binding.calificacionText.text = calificacion
    }

    override fun getItemCount(): Int = calificaciones.size
}
