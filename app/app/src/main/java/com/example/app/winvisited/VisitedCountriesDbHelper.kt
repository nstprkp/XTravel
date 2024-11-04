package com.example.app.winvisited

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VisitedCountriesDbHelper(val context: Context) : SQLiteOpenHelper(context, "appbdcountries", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createVisitedCountriesTable = """
            CREATE TABLE countries (
                id INTEGER PRIMARY KEY AUTOINCREMENT, 
                login TEXT,
                country_name TEXT
            )
        """.trimIndent()

        db.execSQL(createVisitedCountriesTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS countries")
        onCreate(db)
    }

    fun addCountryForUser(country: VisitedCountry): Int? {
        val values = ContentValues().apply {
            put("login", country.login)
            put("country_name", country.name)
        }

        writableDatabase.use { db ->
            val result = db.insert("countries", null, values).toInt()
            return if (result == -1) null else result
        }
    }

    fun deleteVisitedCountryById(id: Int) {
        val db = this.writableDatabase
        db.delete("countries", "id = ?", arrayOf(id.toString()))
        db.close()
    }

    @SuppressLint("Range")
    fun getCountriesForUser(login: String): List<VisitedCountry> {
        val countriesList = mutableListOf<VisitedCountry>()
        val query = "SELECT * FROM countries WHERE login = ?"

        readableDatabase.rawQuery(query, arrayOf(login)).use { cursor ->
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    val name = cursor.getString(cursor.getColumnIndex("country_name"))

                    val country = VisitedCountry(id, login, name)
                    countriesList.add(country)
                } while (cursor.moveToNext())
            }
        }

        return countriesList
    }

    @SuppressLint("Range")
    fun isVisitedCountryExists(login: String, countryName: String): Boolean {
        val query = "SELECT * FROM countries WHERE login = ? AND country_name = ?"
        val cursor = readableDatabase.rawQuery(query, arrayOf(login, countryName))

        val exists = cursor.count > 0 // Если найден хотя бы один результат, значит страна уже добавлена
        cursor.close() // Закрываем курсор после использования
        return exists
    }

}