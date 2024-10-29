package com.example.app.winticket

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TicketsDbHelper(val context: Context) : SQLiteOpenHelper(context, "appbdticketsa", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        val createTicketsTable = """
            CREATE TABLE tickets (
                id INTEGER PRIMARY KEY AUTOINCREMENT, 
                login TEXT, 
                from_country_and_town TEXT, 
                to_country_and_town TEXT, 
                transport_type TEXT, 
                departure_data TEXT,
                departure_time TEXT,
                arrival_data TEXT,
                arrival_time TEXT,
                place TEXT
            )
        """.trimIndent()

        db.execSQL(createTicketsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS tickets")
        onCreate(db)
    }

    fun addTicketForUser(ticket: Ticket): Int? {
        val values = ContentValues().apply {
            put("login", ticket.login)
            put("from_country_and_town", ticket.fromCountryAndTown)
            put("to_country_and_town", ticket.toCountryAndTown)
            put("transport_type", ticket.transportType)
            put("departure_data", ticket.departureData)
            put("departure_time", ticket.departureTime)
            put("arrival_data", ticket.arrivalData)
            put("arrival_time", ticket.arrivalTime)
            put("place", ticket.place)
        }

        writableDatabase.use { db ->
            val result = db.insert("tickets", null, values).toInt()
            return if (result == -1) null else result
        }
    }

    fun deleteTicketById(id: Int) {
        val db = this.writableDatabase
        db.delete("tickets", "id = ?", arrayOf(id.toString()))
        db.close()
    }

    @SuppressLint("Range")
    fun getTicketsByLogin(login: String): List<Ticket> {
        val ticketList = mutableListOf<Ticket>()
        val query = "SELECT * FROM tickets WHERE login = ?"

        readableDatabase.rawQuery(query, arrayOf(login)).use { cursor ->
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    val fromCountry = cursor.getString(cursor.getColumnIndex("from_country_and_town"))
                    val toCountry = cursor.getString(cursor.getColumnIndex("to_country_and_town"))
                    val transportType = cursor.getString(cursor.getColumnIndex("transport_type"))
                    val departureData = cursor.getString(cursor.getColumnIndex("departure_data"))
                    val departureTime = cursor.getString(cursor.getColumnIndex("departure_time"))
                    val arrivalData = cursor.getString(cursor.getColumnIndex("arrival_data"))
                    val arrivalTime = cursor.getString(cursor.getColumnIndex("arrival_time"))
                    val place = cursor.getString(cursor.getColumnIndex("place"))

                    val ticket = Ticket(id, login, fromCountry, toCountry, transportType, departureData, departureTime, arrivalData, arrivalTime, place)
                    ticketList.add(ticket)
                } while (cursor.moveToNext())
            }
        }
        return ticketList
    }

}