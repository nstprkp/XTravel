package com.example.app.wintrack

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TrackDbHelper(val context: Context) : SQLiteOpenHelper(context, "appbdtrack", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTrackTable = """
            CREATE TABLE tracks (
                id INTEGER PRIMARY KEY AUTOINCREMENT, 
                login TEXT, 
                start_location TEXT, 
                end_location TEXT, 
                stop_points TEXT
            )
        """.trimIndent()

        db.execSQL(createTrackTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS tracks")
        onCreate(db)
    }

    fun addTrackForUser(track: Track): Int? {
        val values = ContentValues().apply {
            put("login", track.login)
            put("start_location", track.startLocation)
            put("end_location", track.endLocation)
            put("stop_points", track.stops.joinToString(","))
        }

        writableDatabase.use { db ->
            val result = db.insert("tracks", null, values).toInt()
            return if (result == -1) null else result
        }
    }

    fun deleteTrackById(id: Int) {
        val db = this.writableDatabase
        db.delete("tracks", "id = ?", arrayOf(id.toString()))
        db.close()
    }

    @SuppressLint("Range")
    fun getTrackByLogin(login: String): List<Track> {
        val trackList = mutableListOf<Track>()
        val query = "SELECT * FROM tracks WHERE login = ?"

        readableDatabase.rawQuery(query, arrayOf(login)).use { cursor ->
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    val startLocation = cursor.getString(cursor.getColumnIndex("start_location"))
                    val endLocation = cursor.getString(cursor.getColumnIndex("end_location"))
                    val stopPoints = cursor.getString(cursor.getColumnIndex("stop_points")).split(",").filter { it.isNotEmpty() }

                    val track = Track(id, login, startLocation, endLocation, stopPoints)
                    trackList.add(track)
                } while (cursor.moveToNext())
            }
        }
        return trackList
    }

}