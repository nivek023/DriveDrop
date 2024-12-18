package com.example.drivedrop.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.drivedrop.entities.Owner
import com.example.drivedrop.entities.Car

//represents the relation between owner and cars. An owner can own more than one car.
data class OwnerHasCars (
    @Embedded val owner: Owner,
    @Relation(
        parentColumn = "ownerId",
        entityColumn = "ownerId"
    )
    val cars: List<Car>,
)