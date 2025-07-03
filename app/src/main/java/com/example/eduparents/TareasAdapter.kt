package com.example.eduparents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eduparents.databinding.ItemTareaBinding

data class Tarea(val titulo: String, val descripcion: String)

class TareasAdapter(private val tareas: List<Tarea>) :
    RecyclerView.Adapter<TareasAdapter.TareaViewHolder>() {

    inner class TareaViewHolder(val binding: ItemTareaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val binding = ItemTareaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TareaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = tareas[position]
        holder.binding.tareaTitulo.text = tarea.titulo
        holder.binding.tareaDescripcion.text = tarea.descripcion
    }

    override fun getItemCount(): Int = tareas.size
}
