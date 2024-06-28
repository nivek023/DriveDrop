package com.example.drivedrop.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.drivedrop.entities.Owner
import com.example.drivedrop.entities.User

// represents the relation: an owner is a user.
data class UserIsOwner (
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val owner: Owner,
)