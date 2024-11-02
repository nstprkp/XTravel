package com.example.app.wintrack

data class Track(val id: Int = 0, val login: String, val startLocation: String, val endLocation: String, val stops: List<String>) {
}