package com.example.app.winvisa

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
import com.google.android.material.snackbar.Snackbar

class VisasActivity : AppCompatActivity() {

    private lateinit var visaAdapter: VisaAdapter
    private lateinit var visaList: MutableList<Visa>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visas)

        val sharedPreferences = getSharedPreferences("unique_user_prefs", MODE_PRIVATE)
        val userLogin = sharedPreferences.getString("USER_LOGIN", "")

        if (!userLogin.isNullOrEmpty()) {
            val visasDbHelper = VisasDbHelper(this)

            visaList = visasDbHelper.getVisasByLogin(userLogin)

            val visasList: RecyclerView = findViewById(R.id.rvVisas)
            val visas = arrayListOf<Visa>()

            visas.addAll(visaList)

            visasList.layoutManager = LinearLayoutManager(this)
            visasList.adapter = VisaAdapter(visas, this)

            val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false // Перемещение не нужно, только свайп
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val deletedVisa = visaList[position]

                    showDeleteConfirmationDialog(deletedVisa, visasDbHelper, position)
                }
            }

            ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(visasList)

        }


        val btnAddVisa = findViewById<Button>(R.id.btnAddVisa)
        btnAddVisa.setOnClickListener {
            val intent = Intent(this, AddVisaActivity::class.java)
            startActivity(intent)
        }

        val btnBackToAccount: Button = findViewById(R.id.btnBackToAccount)
        btnBackToAccount.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }

    }

    private fun showDeleteConfirmationDialog(visa: Visa, visasDbHelper: VisasDbHelper, position: Int) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_notification_delete, null)
        val dialog = AlertDialog.Builder(this).setView(dialogView).create()

        dialogView.findViewById<Button>(R.id.yes_button).setOnClickListener {
            visasDbHelper.deleteVisaById(visa.id)
            dialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.no_button).setOnClickListener {
            val intent = Intent(this, VisasActivity::class.java)
            startActivity(intent)

            dialog.dismiss()
        }

        dialog.show()
    }

}