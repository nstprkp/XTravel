package com.example.app.wintrack

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import com.example.app.winmain.MapActivity

class AddTrackActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_track)

        val trackDbHelper = TrackDbHelper(this)

        val etStartOfTrack = findViewById<EditText>(R.id.etStartOfTrack)
        val etEndOfTrack = findViewById<EditText>(R.id.etFinishOfTrack)
        val stopPoint1 = findViewById<EditText>(R.id.etStopPoint1)
        val stopPoint2 = findViewById<EditText>(R.id.etStopPoint2)
        val stopPoint3 = findViewById<EditText>(R.id.etStopPoint3)
        val stopPoint4 = findViewById<EditText>(R.id.etStopPoint4)
        val stopPoint5 = findViewById<EditText>(R.id.etStopPoint5)


        val sharedPreferences = getSharedPreferences("unique_user_prefs", MODE_PRIVATE)
        val userLogin = sharedPreferences.getString("USER_LOGIN", "")

        val btnSaveTrack: Button = findViewById(R.id.btnSaveTrack)
        btnSaveTrack.setOnClickListener {
            val startLocation = etStartOfTrack.text.toString().trim()
            val endLocation = etEndOfTrack.text.toString().trim()
            val stops = listOf(
                stopPoint1.text.toString().trim(),
                stopPoint2.text.toString().trim(),
                stopPoint3.text.toString().trim(),
                stopPoint4.text.toString().trim(),
                stopPoint5.text.toString().trim()
            ).filter { it.isNotEmpty() }

            if (!userLogin.isNullOrEmpty() && startLocation.isNotEmpty() && endLocation.isNotEmpty()) {
                val newTrack = Track(login = userLogin,
                    startLocation = startLocation,
                    endLocation = endLocation,
                    stops = stops
                )

                val trackId = trackDbHelper.addTrackForUser(newTrack)
                if (trackId != null) {
                    Toast.makeText(this, "Маршрут успешно добавлен", Toast.LENGTH_SHORT).show()
                }

                val intent = Intent(this, MapActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Введите начальную и конечную точку маршрута", Toast.LENGTH_SHORT).show()
            }
        }

        val btnGoBack: Button = findViewById(R.id.btn_go_back_to_track)
        btnGoBack.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
    }
}