package com.example.drivedrop.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

//the entity class for the table Owner
@Entity
data class Owner (
    @PrimaryKey(autoGenerate = true)
    val ownerId: Int? = null,
    //reference to UserID
    val id : Int,
    val costCenter : Int,
)