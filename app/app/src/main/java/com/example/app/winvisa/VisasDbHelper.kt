package com.example.app.winvisa

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VisasDbHelper(val context: Context) : SQLiteOpenHelper(context, "appbdvisa", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createVisasTable = """
            CREATE TABLE visas (
                id INTEGER PRIMARY KEY AUTOINCREMENT, 
                login TEXT, 
                country TEXT, 
                visa_type TEXT, 
                issue_date TEXT, 
                expiry_date TEXT
            )
        """.trimIndent()

        db.execSQL(createVisasTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS visas")
        onCreate(db)
    }

    fun addVisaForUser(visa: Visa): Int? {
        val values = ContentValues().apply {
            put("login", visa.login)
            put("country", visa.country)
            put("visa_type", visa.visaType)
            put("issue_date", visa.issueDate)
            put("expiry_date", visa.expiryDate)
        }

        writableDatabase.use { db ->
            val result = db.insert("visas", null, values).toInt()
            return if (result == -1) null else result
        }
    }

    fun deleteVisaById(id: Int) {
        val db = this.writableDatabase
        db.delete("visas", "id = ?", arrayOf(id.toString()))
        db.close()
    }

    @SuppressLint("Range")
    fun getVisasByLogin(login: String): MutableList<Visa> {
        val visaList = mutableListOf<Visa>()
        val query = "SELECT * FROM visas WHERE login = ?"

        readableDatabase.rawQuery(query, arrayOf(login)).use { cursor ->
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    val country = cursor.getString(cursor.getColumnIndex("country"))
                    val visaType = cursor.getString(cursor.getColumnIndex("visa_type"))
                    val issueDate = cursor.getString(cursor.getColumnIndex("issue_date"))
                    val expiryDate = cursor.getString(cursor.getColumnIndex("expiry_date"))

                    val visa = Visa(id, login, country, visaType, issueDate, expiryDate)
                    visaList.add(visa)
                } while (cursor.moveToNext())
            }
        }
        return visaList
    }
}