package com.example.app.winmain

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.app.winlog.AuthActivity
import com.example.app.R
import com.example.app.SavedPlacesActivity
import com.example.app.winsaved.SavedAdapter
import com.example.app.winticket.TicketsActivity
import com.example.app.winvisa.VisasActivity

class AccountActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val buttonGoToAccount: ImageButton = findViewById(R.id.account_button_acc)
        val buttonGoToMap: ImageButton = findViewById(R.id.map_button_acc)
        val buttonGoToPlaces: ImageButton = findViewById(R.id.places_button_acc)
        val buttonGoToPictures: ImageButton = findViewById(R.id.picture_button_acc)

        buttonGoToPlaces.setOnClickListener {
            val intent = Intent(this, PlacesActivity::class.java)
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

        val buttonVisas: Button = findViewById(R.id.link_to_visa)
        buttonVisas.setOnClickListener {
            val intent = Intent(this, VisasActivity::class.java)
            startActivity(intent)
        }

        val buttonTickets: Button = findViewById(R.id.link_to_tickets)
        buttonTickets.setOnClickListener {
            val intent = Intent(this, TicketsActivity::class.java)
            startActivity(intent)
        }

        val buttonSavedPlaces: Button = findViewById(R.id.link_to_saved)
        buttonSavedPlaces.setOnClickListener {
            val intent = Intent(this, SavedPlacesActivity::class.java)
            startActivity(intent)
        }

        val buttonLogOut: Button = findViewById(R.id.link_to_log_out)
        buttonLogOut.setOnClickListener {
            val sharedExistAuth = getSharedPreferences("user_already_started", MODE_PRIVATE)
            val editorStart = sharedExistAuth.edit()
            editorStart.putBoolean("isLoggedIn", false)
            editorStart.apply()
            val intent = Intent(this, AuthActivity::class.java)

            startActivity(intent)
        }

    }

}