package com.example.app.winticket

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import com.example.app.winvisa.Visa

class AddTicketActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ticket)

        val ticketDbHelper = TicketsDbHelper(this)

        val etFromCountry = findViewById<EditText>(R.id.tickAddCountryFrom)
        val etToCountry = findViewById<EditText>(R.id.tickAddCountryTo)
        val etDepartureData = findViewById<EditText>(R.id.tickAddDataDeparture)
        val etDepartureTime = findViewById<EditText>(R.id.tickAddTimeDeparture)
        val etArrivalData = findViewById<EditText>(R.id.tickAddDataArrival)
        val etArrivalTime = findViewById<EditText>(R.id.tickAddTimeArrival)
        val etPlace = findViewById<EditText>(R.id.tickAddPlace)
        val etTransportType = findViewById<EditText>(R.id.tickAddTransportType)


        val sharedPreferences = getSharedPreferences("unique_user_prefs", MODE_PRIVATE)
        val userLogin = sharedPreferences.getString("USER_LOGIN", "")

        val btnSaveTicket = findViewById<Button>(R.id.btnSaveTicket)
        btnSaveTicket.setOnClickListener {
            val fromCountry = etFromCountry.text.toString().trim()
            val toCountry = etToCountry.text.toString().trim()
            val departureData = etDepartureData.text.toString().trim()
            val departureTime = etDepartureTime.text.toString().trim()
            val arrivalData = etArrivalData.text.toString().trim()
            val arrivalTime = etArrivalTime.text.toString().trim()
            val place = etPlace.text.toString().trim()
            val transportType = etTransportType.text.toString().trim()

            if (!userLogin.isNullOrEmpty() && fromCountry.isNotEmpty() && toCountry.isNotEmpty() && departureData.isNotEmpty() && departureTime.isNotEmpty() && arrivalData.isNotEmpty() && arrivalTime.isNotEmpty() && place.isNotEmpty() && transportType.isNotEmpty()) {


                /*val newTicket = Ticket(
                    userLogin,
                    fromCountry,
                    toCountry,
                    transportType,
                    departureData, departureTime,
                    arrivalData, arrivalTime,
                    place
                )*/

                val newTicket = Ticket(login = userLogin, fromCountryAndTown = fromCountry, toCountryAndTown = toCountry,
                    transportType = transportType, departureData = departureData, departureTime = departureTime,
                    arrivalData = arrivalData, arrivalTime = arrivalTime, place = place)

                val newTicketId = ticketDbHelper.addTicketForUser(newTicket)

                if (newTicketId != null) {
                    val ticketWithId = newTicket.copy(id = newTicketId)
                }

                val intent = Intent(this, TicketsActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        val btnGoBack = findViewById<Button>(R.id.btn_go_back_to_acc)
        btnGoBack.setOnClickListener {
            val intent = Intent(this, TicketsActivity::class.java)
            startActivity(intent)
        }
    }
}