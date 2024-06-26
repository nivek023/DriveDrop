package com.example.drivedrop.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Driver (
    @PrimaryKey(autoGenerate = true)
    val driverId: Int? = null,
    //reference for UserID
    val id: Int,
    val currentTour: String,
    val rating: String,
    val favorites: List<String>,
    val driverLicence: String,
)