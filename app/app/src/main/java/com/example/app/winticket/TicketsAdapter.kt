package com.example.app.winticket

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R

class TicketsAdapter(private val ticketList: List<Ticket>, var context: Context) : RecyclerView.Adapter<TicketsAdapter.TicketsViewHolder>(){

    class TicketsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tickToCountry: TextView = itemView.findViewById(R.id.tickToCountry)
        val tickFromCountry: TextView = itemView.findViewById(R.id.tickFromCountry)
        val tickTransportType: TextView = itemView.findViewById(R.id.tickTransportType)
        val tickDepartureData: TextView = itemView.findViewById(R.id.tickDepartureData)
        val tickDepartureTime: TextView = itemView.findViewById(R.id.tickDepartureTime)
        val tickArrivalData: TextView = itemView.findViewById(R.id.tickArrivalData)
        val tickArrivalTime: TextView = itemView.findViewById(R.id.tickArrivalTime)
        val tickPlace: TextView = itemView.findViewById(R.id.tickPlace)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ticket_item, parent, false)
        return TicketsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TicketsViewHolder, position: Int) {
        val ticket = ticketList[position]
        holder.tickToCountry.text = ticket.toCountryAndTown
        holder.tickFromCountry.text = "Откуда: ${ticket.fromCountryAndTown}"
        holder.tickTransportType.text = "Тип транспорта: ${ticket.transportType}"
        holder.tickPlace.text = "Посадочное место: ${ticket.place}"
        holder.tickDepartureData.text = "Дата отправления: ${ticket.departureData}"
        holder.tickDepartureTime.text = "Время отправления: ${ticket.departureTime}"
        holder.tickArrivalData.text = "Дата прибытия: ${ticket.arrivalData}"
        holder.tickArrivalTime.text = "Время прибытия: ${ticket.arrivalTime}"
    }

    override fun getItemCount() = ticketList.size
}