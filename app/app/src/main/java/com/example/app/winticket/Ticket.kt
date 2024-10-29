package com.example.app.winticket

data class Ticket(val id: Int = 0, val login: String, val fromCountryAndTown: String, val toCountryAndTown: String, val transportType: String, val departureData: String, val departureTime: String, val arrivalData: String, val arrivalTime: String, val place: String) {
}