package com.example.app.winmain

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.app.wintrack.AddTrackActivity
import com.example.app.R

class MapActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val buttonGoToAccount: ImageButton = findViewById(R.id.account_button_mp)
        val buttonGoToMap: ImageButton = findViewById(R.id.map_button_mp)
        val buttonGoToPlaces: ImageButton = findViewById(R.id.places_button_mp)
        val buttonGoToPictures: ImageButton = findViewById(R.id.picture_button_mp)

        buttonGoToAccount.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }
        buttonGoToPictures.setOnClickListener {
            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }
        buttonGoToPlaces.setOnClickListener {
            val intent = Intent(this, PlacesActivity::class.java)
            startActivity(intent)
        }

        val buttonAddTrack: Button = findViewById(R.id.add_track_button)
        buttonAddTrack.setOnClickListener {
            val intent = Intent(this, AddTrackActivity::class.java)
            startActivity(intent)
        }
    }
}