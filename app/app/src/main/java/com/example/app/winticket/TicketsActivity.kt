package com.example.app.winticket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.winmain.AccountActivity
import com.example.app.R
import com.example.app.winvisa.Visa
import com.example.app.winvisa.VisasActivity
import com.example.app.winvisa.VisasDbHelper

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
                    val deletedTicket = ticketList[position]

                    showDeleteConfirmationDialog(deletedTicket, ticketsDbHelper, position)
                }
            }

            ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(ticketsList)
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

    private fun showDeleteConfirmationDialog(ticket: Ticket, ticketsDbHelper: TicketsDbHelper, position: Int) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_notification_delete, null)
        val dialog = AlertDialog.Builder(this).setView(dialogView).create()

        dialogView.findViewById<Button>(R.id.yes_button).setOnClickListener {
            ticketsDbHelper.deleteTicketById(ticket.id)
            dialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.no_button).setOnClickListener {
            val intent = Intent(this, TicketsActivity::class.java)
            startActivity(intent)

            dialog.dismiss()
        }

        dialog.show()
    }
}