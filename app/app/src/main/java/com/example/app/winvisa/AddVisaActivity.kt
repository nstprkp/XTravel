package com.example.app.winvisa

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R

class AddVisaActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_visa)

        val visasDbHelper = VisasDbHelper(this)

        val etCountry = findViewById<EditText>(R.id.etCountry)
        val etVisaType = findViewById<EditText>(R.id.etVisaType)
        val etIssueDate = findViewById<EditText>(R.id.etIssueDate)
        val etExpiryDate = findViewById<EditText>(R.id.etExpiryDate)
        val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val userLogin = sharedPreferences.getString("USER_LOGIN", "")


        val btnSaveVisa = findViewById<Button>(R.id.btnSaveVisa)
        btnSaveVisa.setOnClickListener {
            val country = etCountry.text.toString()
            val visaType = etVisaType.text.toString()
            val issueDate = etIssueDate.text.toString()
            val expiryDate = etExpiryDate.text.toString()

            // Проверка на заполненность всех полей
            if (!userLogin.isNullOrEmpty() && country.isNotEmpty() && visaType.isNotEmpty() && issueDate.isNotEmpty() && expiryDate.isNotEmpty()) {
                //val newVisa = Visa(userLogin, country, visaType, issueDate, expiryDate)

                val newVisa = Visa(login = userLogin, country = country, visaType = visaType, issueDate = issueDate, expiryDate = expiryDate)
                val newVisaId = visasDbHelper.addVisaForUser(newVisa)

                if (newVisaId != null) {
                    val visaWithId = newVisa.copy(id = newVisaId)
                }

                val intent = Intent(this, VisasActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        val btnGoBack = findViewById<Button>(R.id.btn_go_back)
        btnGoBack.setOnClickListener {
            val intent = Intent(this, VisasActivity::class.java)
            startActivity(intent)
        }
    }
}