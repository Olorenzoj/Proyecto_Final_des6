package com.example.eduparents

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "eduparents.db", null, 4) {

    override fun onCreate(db: SQLiteDatabase) {
        // Tabla de usuarios
        db.execSQL("""
            CREATE TABLE usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre TEXT,
            correo TEXT,
            usuario TEXT UNIQUE,
            contrasena TEXT
            )
        """.trimIndent())

        // Tabla calificaciones
        db.execSQL("""
        CREATE TABLE calificaciones (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            usuario TEXT,
            materia TEXT,
            nota REAL,
            FOREIGN KEY (usuario) REFERENCES usuarios(usuario)
        )
    """.trimIndent())

        // Usuario de prueba
        val values = ContentValues().apply {
            put("nombre", "Padre de Prueba")
            put("correo", "padre@ejemplo.com")
            put("usuario", "padre")
            put("contrasena", "1234")
        }
        db.insert("usuarios", null, values)

        // Calificaciones de prueba para ese usuario (varias notas por materia)
        val materiasConNotas = mapOf(
            "Matemáticas" to listOf(4.5, 3.8, 4.9),
            "Ciencias" to listOf(4.2, 4.0),
            "Español" to listOf(4.8, 4.5, 4.7, 5.0),
            "Inglés" to listOf(4.0),
            "Historia" to listOf(4.6, 4.1)
        )

        for ((materia, notas) in materiasConNotas) {
            for (nota in notas) {
                val califValues = ContentValues().apply {
                    put("usuario", "padre")
                    put("materia", materia)
                    put("nota", nota)
                }
                db.insert("calificaciones", null, califValues)
            }
        }

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS calificaciones")
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }

    fun verificarCredenciales(usuario: String, contrasena: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?",
            arrayOf(usuario, contrasena)
        )
        val valido = cursor.count > 0
        cursor.close()
        return valido
    }

    fun registrarUsuario(nombre: String, correo: String, usuario: String, contrasena: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nombre", nombre)
            put("correo", correo)
            put("usuario", usuario)
            put("contrasena", contrasena)
        }
        return try {
            db.insertOrThrow("usuarios", null, values) != -1L
        } catch (e: Exception) {
            false
        }
    }

    fun obtenerCalificacionesPorUsuario(usuario: String): List<Pair<String, Double>> {
        val calificaciones = mutableListOf<Pair<String, Double>>()
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT materia, nota FROM calificaciones WHERE usuario = ?",
            arrayOf(usuario)
        )
        if (cursor.moveToFirst()) {
            do {
                val materia = cursor.getString(cursor.getColumnIndexOrThrow("materia"))
                val nota = cursor.getDouble(cursor.getColumnIndexOrThrow("nota"))
                calificaciones.add(materia to nota)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return calificaciones
    }

    fun obtenerNotasAgrupadasPorMateria(usuario: String): Map<String, List<Double>> {
        val db = readableDatabase
        val resultado = mutableMapOf<String, MutableList<Double>>()

        val cursor = db.rawQuery(
            "SELECT materia, nota FROM calificaciones WHERE usuario = ?",
            arrayOf(usuario)
        )

        if (cursor.moveToFirst()) {
            do {
                val materia = cursor.getString(cursor.getColumnIndexOrThrow("materia"))
                val nota = cursor.getDouble(cursor.getColumnIndexOrThrow("nota"))
                resultado.getOrPut(materia) { mutableListOf() }.add(nota)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return resultado
    }


}
