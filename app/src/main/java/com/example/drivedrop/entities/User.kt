package com.example.drivedrop.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

////the entity class for the table User
@Entity
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val userName: String,
    val paymentInfo: String,
    val bio: String,
)