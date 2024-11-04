// VisitedCountryAdapter.kt
package com.example.app.winvisited

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R

class VisitedCountryAdapter(private val visitedCountries: List<VisitedCountry>, var context: Context) : RecyclerView.Adapter<VisitedCountryAdapter.VisitedCountryViewHolder>() {

    class VisitedCountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countryName: TextView = itemView.findViewById(R.id.countryNameVisited)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitedCountryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.visited_country_item, parent, false)
        return VisitedCountryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VisitedCountryViewHolder, position: Int) {
        val visitedCountry = visitedCountries[position]
        holder.countryName.text = visitedCountry.name
    }

    override fun getItemCount() = visitedCountries.size
}
