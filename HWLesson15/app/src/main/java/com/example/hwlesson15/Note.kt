package com.example.hwlesson15

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val title: String,
    val description: String,
    val date: String
) : Parcelable
