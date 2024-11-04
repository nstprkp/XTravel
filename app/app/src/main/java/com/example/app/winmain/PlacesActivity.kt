package com.example.app.winmain

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
import com.example.app.winvisited.AddVisitedCountryActivity
import com.example.app.R
import com.example.app.winvisited.VisitedCountriesDbHelper
import com.example.app.winvisited.VisitedCountry
import com.example.app.winvisited.VisitedCountryAdapter


class PlacesActivity : AppCompatActivity() {

    private lateinit var visitedAdapter: VisitedCountryAdapter
    private lateinit var visitedCountryList: List<VisitedCountry>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places)

        val sharedPreferences = getSharedPreferences("unique_user_prefs", MODE_PRIVATE)
        val userLogin = sharedPreferences.getString("USER_LOGIN", "")

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

        val buttonAddVisited: Button = findViewById(R.id.add_visited_place_button)
        buttonAddVisited.setOnClickListener {
            val intent = Intent(this, AddVisitedCountryActivity::class.java)
            startActivity(intent)
        }

        if (!userLogin.isNullOrEmpty()) {
            val visitedDbHelper = VisitedCountriesDbHelper(this)

            visitedCountryList = visitedDbHelper.getCountriesForUser(userLogin)

            val countriesList: RecyclerView = findViewById(R.id.visited_places_List)
            val visitedCountries = arrayListOf<VisitedCountry>()

            visitedCountries.addAll(visitedCountryList)

            countriesList.layoutManager = LinearLayoutManager(this)
            countriesList.adapter = VisitedCountryAdapter(visitedCountries, this)


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
                    val deletedVisitedCountry = visitedCountryList[position]

                    showDeleteConfirmationDialog(deletedVisitedCountry, visitedDbHelper, position)
                }
            }

            ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(countriesList)
        }
    }

    private fun showDeleteConfirmationDialog(visitedCountry: VisitedCountry, dbHelper: VisitedCountriesDbHelper, position: Int) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_notification_delete, null)
        val dialog = AlertDialog.Builder(this).setView(dialogView).create()

        dialogView.findViewById<Button>(R.id.yes_button).setOnClickListener {
            dbHelper.deleteVisitedCountryById(visitedCountry.id)
            dialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.no_button).setOnClickListener {
            val intent = Intent(this, PlacesActivity::class.java)
            startActivity(intent)

            dialog.dismiss()
        }

        dialog.show()
    }
}