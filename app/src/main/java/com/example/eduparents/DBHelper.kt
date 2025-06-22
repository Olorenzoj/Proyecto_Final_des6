package com.example.eduparents

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "eduparents.db", null, 2) {

    override fun onCreate(db: SQLiteDatabase) {
        // Crear tabla de usuarios
        db.execSQL("""
            CREATE TABLE usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre TEXT,
            correo TEXT,
            usuario TEXT UNIQUE,
            contrasena TEXT
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
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
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


}
