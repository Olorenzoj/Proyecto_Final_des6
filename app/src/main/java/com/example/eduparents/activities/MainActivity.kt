package com.example.eduparents.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.eduparents.R
import com.example.eduparents.databinding.MainActivityBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el NavHostFragment correctamente
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // Obtener NavController desde el NavHostFragment
        navController = navHostFragment.navController

        val navView: BottomNavigationView = binding.navigation

        // Conectar BottomNavigationView con NavController
        navView.setupWithNavController(navController)

        // Recibir nombre de usuario de LoginActivity
        val nombreUsuario = intent.getStringExtra("nombreUsuario")

        // Pasar nombre de usuario al fragment inicial
        val bundle = Bundle().apply {
            putString("nombreUsuario", nombreUsuario)
        }

        // Establecer argumentos en el gráfico de navegación
        navController.setGraph(R.navigation.mobile_navigation, bundle)
    }
}