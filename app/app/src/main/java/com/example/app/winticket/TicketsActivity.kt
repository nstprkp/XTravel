package com.example.app.winticket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.winmain.AccountActivity
import com.example.app.R

class TicketsActivity : AppCompatActivity() {

    private lateinit var ticketAdapter: TicketsAdapter
    private lateinit var ticketList: List<Ticket>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)

        val sharedPreferences = getSharedPreferences("unique_user_prefs", MODE_PRIVATE)
        val userLogin = sharedPreferences.getString("USER_LOGIN", "")

        if (!userLogin.isNullOrEmpty()) {
            val ticketsDbHelper = TicketsDbHelper(this)

            ticketList = ticketsDbHelper.getTicketsByLogin(userLogin)

            val ticketsList: RecyclerView = findViewById(R.id.rvTickets)
            val tickets = arrayListOf<Ticket>()

            tickets.addAll(ticketList)

            ticketsList.layoutManager = LinearLayoutManager(this)
            ticketsList.adapter = TicketsAdapter(tickets, this)
        }

        val btnAddTicket = findViewById<Button>(R.id.btnAddTicket)
        btnAddTicket.setOnClickListener {
            val intent = Intent(this, AddTicketActivity::class.java)
            startActivity(intent)
        }

        val btnGoBackToAccount: Button = findViewById(R.id.btnGoBackToAccount)
        btnGoBackToAccount.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }
    }
}