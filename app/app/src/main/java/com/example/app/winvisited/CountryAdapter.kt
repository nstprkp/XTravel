// CountryAdapter.kt
package com.example.app.winvisited

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import java.util.Locale

class CountryAdapter(
    private val context: Context,
    private val countries: MutableList<Country>, // Используем изменяемый список
    private val dbHelper: VisitedCountriesDbHelper, // Передаем в адаптер экземпляр БД
    private val userLogin: String
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), Filterable {

    private val fullCountryList: List<Country> = ArrayList(countries) // Копия полного списка

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        return CountryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.bind(country)
    }

    override fun getItemCount() = countries.size

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val countryNameTextView: TextView = itemView.findViewById(R.id.countryNameTextView)
        private val actionButton: Button = itemView.findViewById(R.id.actionButton)

        fun bind(country: Country) {
            countryNameTextView.text = country.name

            actionButton.setOnClickListener {
                addCountryToDatabase(country)
            }
        }

        private fun addCountryToDatabase(country: Country) {
            if (dbHelper.isVisitedCountryExists(userLogin, country.name)) {
                Toast.makeText(context, "Страна уже отмечена как посещенная.", Toast.LENGTH_SHORT).show()
            } else {
                val visitedCountry = VisitedCountry(login = userLogin, name = country.name)
                val result = dbHelper.addCountryForUser(visitedCountry)
                if (result != null) {
                    Toast.makeText(context, "${country.name} добавлена в посещенные страны.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Ошибка при добавлении страны.", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(query: CharSequence?): FilterResults {
                val filteredList = if (query.isNullOrEmpty()) {
                    fullCountryList
                } else {
                    fullCountryList.filter {
                        it.name.lowercase(Locale.getDefault()).contains(query.toString().lowercase(Locale.getDefault()))
                    }
                }

                return FilterResults().apply {
                    values = filteredList
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countries.clear()
                if (results?.values is List<*>) {
                    countries.addAll(results.values as List<Country>)
                }
                notifyDataSetChanged()
            }
        }
    }
}
