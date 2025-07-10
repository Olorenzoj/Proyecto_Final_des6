package com.example.eduparents.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eduparents.databinding.ItemEventoBinding

class ScholarsEventsAdapter(private val eventos: List<String>) :
    RecyclerView.Adapter<ScholarsEventsAdapter.EventoViewHolder>() {

    inner class EventoViewHolder(val binding: ItemEventoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val binding = ItemEventoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        holder.binding.eventoText.text = eventos[position]
    }

    override fun getItemCount(): Int = eventos.size
}
