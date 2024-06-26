package com.example.drivedrop.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Chat (
    @PrimaryKey(autoGenerate = true)
    val chatId: Int? = null,
    //reference to ownerID
    val ownerId : Int,
    //reference to driverID
    val driverId : Int,
    //reference to tourID
    val tourId : Int,
    val messages : List<String>,
)