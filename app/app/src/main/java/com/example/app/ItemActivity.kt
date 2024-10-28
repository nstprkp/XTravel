package com.example.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.app.winmain.ItemsActivity
import com.example.app.winsaved.SavedPlaces
import com.example.app.winsaved.SavedPlacesDbHelper

class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val userLogin = sharedPreferences.getString("USER_LOGIN", "") ?: ""

        val savedPlacesDbHelper = SavedPlacesDbHelper(this)
        val title: TextView = findViewById(R.id.item_list_title1)
        val text: TextView = findViewById(R.id.item_list_text)

        title.text = intent.getStringExtra("itemTitle")
        text.text = intent.getStringExtra("itemText")

        val backButton: Button = findViewById(R.id.dislike_button)

        backButton.setOnClickListener {
            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }

        val addToFavoriteButton: Button = findViewById(R.id.like_button)
        addToFavoriteButton.setOnClickListener {
            val etImage = intent.getStringExtra("itemImage") ?: ""
            val etTitle = intent.getStringExtra("itemTitle") ?: ""
            val etDesc = intent.getStringExtra("itemDesc") ?: ""

            val newFavoritePlace = SavedPlaces(login = userLogin, image = etImage, title = etTitle, desc = etDesc)
            val newFavPlaceId = savedPlacesDbHelper.addFavoritePlaceForUser(newFavoritePlace)

            if (newFavPlaceId != null) {
                val favPlaceWithId = newFavoritePlace.copy(id = newFavPlaceId)
            }
        }

    }
}