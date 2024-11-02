package com.example.app.winmain

import TrackAdapter
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.wintrack.AddTrackActivity
import com.example.app.R
import com.example.app.winticket.TicketsDbHelper
import com.example.app.wintrack.Track
import com.example.app.wintrack.TrackDbHelper

class MapActivity: AppCompatActivity() {

    private lateinit var trackAdapter: TrackAdapter
    private lateinit var trackList: List<Track>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val sharedPreferences = getSharedPreferences("unique_user_prefs", MODE_PRIVATE)
        val userLogin = sharedPreferences.getString("USER_LOGIN", "")

        if (!userLogin.isNullOrEmpty()) {
            val tracksDbHelper = TrackDbHelper(this)

            trackList = tracksDbHelper.getTrackByLogin(userLogin)

            val tracksList: RecyclerView = findViewById(R.id.tracksList)
            val tracks = arrayListOf<Track>()

            tracks.addAll(trackList)

            tracksList.layoutManager = LinearLayoutManager(this)
            tracksList.adapter = TrackAdapter(tracks, this)

            val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val deletedTrack = trackList[position]

                    showDeleteConfirmationDialog(deletedTrack, tracksDbHelper, position)
                }
            }

            ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(tracksList)
        }

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

    private fun showDeleteConfirmationDialog(track: Track, trackDbHelper: TrackDbHelper, position: Int) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_notification_delete, null)
        val dialog = AlertDialog.Builder(this).setView(dialogView).create()

        dialogView.findViewById<Button>(R.id.yes_button).setOnClickListener {
            trackDbHelper.deleteTrackById(track.id)
            dialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.no_button).setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)

            dialog.dismiss()
        }

        dialog.show()
    }
}