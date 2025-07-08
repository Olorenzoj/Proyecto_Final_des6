package com.example.eduparents.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.eduparents.databinding.ActivityPanelGeneralBinding

class PanelGeneralActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPanelGeneralBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPanelGeneralBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nombre de usuario recibido desde LoginActivity
        val nombreUsuario = intent.getStringExtra("nombreUsuario") ?: "Padre"
        binding.welcomeText.text = "Bienvenido, $nombreUsuario"

        // Botón: Ver Tareas
        binding.tasksButton.setOnClickListener {
            val intent = Intent(this, TareasActivity::class.java)
            intent.putExtra("nombreUsuario", nombreUsuario)
            startActivity(intent)
        }

        // Botón: Ver Calificaciones
        binding.gradesButton.setOnClickListener {
            val intent = Intent(this, CalificacionesActivity::class.java)
            intent.putExtra("nombreUsuario", nombreUsuario)
            startActivity(intent)
        }


        // Botón: Ver Eventos
        binding.eventsButton.setOnClickListener {
            Toast.makeText(this, "Eventos escolares próximamente", Toast.LENGTH_SHORT).show()
        }

        // Botón: Panel General (Dashboard)
        binding.dashboardButton.setOnClickListener {
            Toast.makeText(this, "Se confecciona un Dashboard resumen", Toast.LENGTH_SHORT).show()
        }

        // Botón: Perfil
        binding.profileButton.setOnClickListener {
            val intent = Intent(this, PerfilActivity::class.java)
            intent.putExtra("nombreUsuario", nombreUsuario)
            startActivity(intent)
        }

        // Botón: Cerrar sesión
        binding.logoutButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
