package com.example.eduparents.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.eduparents.DBHelper
import com.example.eduparents.R
import com.example.eduparents.databinding.FragmentDashboardBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var dbHelper: DBHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDashboardBinding.bind(view)

        dbHelper = DBHelper(requireContext())

        // Recibir el nombre de usuario enviado desde el fragmento anterior
        val nombreUsuario = arguments?.getString("nombreUsuario") ?: "padre"
        val grupo = "6to A" // Cambia si tienes forma de obtenerlo dinámicamente

        binding.studentName.text = nombreUsuario
        binding.studentGroup.text = "Grupo: $grupo"

        // Obtener las calificaciones reales desde la base de datos
        val calificacionesAgrupadas = dbHelper.obtenerNotasAgrupadasPorMateria(nombreUsuario)

        Log.d("DashboardFragment", "Notas agrupadas: $calificacionesAgrupadas")

        if (calificacionesAgrupadas.isEmpty() || calificacionesAgrupadas.values.all { it.isEmpty() }) {
            binding.studentAverage.text = "No hay notas disponibles"
            binding.performanceChart.clear()
            binding.performanceChart.invalidate()
            return
        }

        val promedioGeneral = calificacionesAgrupadas.values.flatten().average()
        binding.studentAverage.text = "Promedio General: %.2f".format(promedioGeneral)

        mostrarGrafico(calificacionesAgrupadas)
    }

    private fun mostrarGrafico(calificacionesAgrupadas: Map<String, List<Double>>) {
        val entries = calificacionesAgrupadas.map { (materia, notas) ->
            val promedio = notas.average().toFloat()
            PieEntry(promedio, materia)
        }

        val dataSet = PieDataSet(entries, "Rendimiento por materia").apply {
            setColors(
                Color.rgb(255, 99, 132),
                Color.rgb(54, 162, 235),
                Color.rgb(255, 206, 86),
                Color.rgb(75, 192, 192)
            )
            valueTextColor = Color.BLACK
            valueTextSize = 14f
        }

        val pieData = PieData(dataSet)

        val chart: PieChart = binding.performanceChart
        chart.data = pieData
        chart.description.isEnabled = false
        chart.centerText = "Rendimiento"
        chart.setEntryLabelColor(Color.BLACK)
        chart.animateY(1000)
        chart.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
