package com.example.app.winmain

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R


class PlacesActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places)

        val buttonGoToAccount: ImageButton = findViewById(R.id.account_button_pl)
        val buttonGoToMap: ImageButton = findViewById(R.id.map_button_pl)
        val buttonGoToPlaces: ImageButton = findViewById(R.id.places_button_pl)
        val buttonGoToPictures: ImageButton = findViewById(R.id.picture_button_pl)

        buttonGoToAccount.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }
        buttonGoToPictures.setOnClickListener {
            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }
        buttonGoToMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
    }
}