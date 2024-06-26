package com.example.drivedrop.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tour (
    @PrimaryKey(autoGenerate = true)
    val tourId: Int? = null,
    //reference to carID
    val carId : Int,
    //reference to driverID
    val driverId : Int,
    //reference to routeID
    val routeId : Int,
    val tip : Int,
    val fuelType : String,
)