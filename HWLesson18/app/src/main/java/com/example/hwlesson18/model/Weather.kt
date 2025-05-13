package com.example.hwlesson18.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val city: City = City()
) : Parcelable

@Parcelize
data class City(
    val city: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0
) : Parcelable
