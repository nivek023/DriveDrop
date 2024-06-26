package com.example.drivedrop.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Car (
    @PrimaryKey(autoGenerate = false)
    val carId: String,
    //reference to ownerID
    val ownerId : Int,
    val time : String,
    val startLocation : String,
    val destination : String,
    val carDetails : String,
    val pictures : String,
    val ratings : String,
)