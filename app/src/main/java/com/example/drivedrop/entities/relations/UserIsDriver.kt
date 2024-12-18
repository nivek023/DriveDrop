package com.example.drivedrop.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.drivedrop.entities.Driver
import com.example.drivedrop.entities.User

// represents the relation: a driver is a user
data class UserIsDriver (
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val driver: Driver,
)