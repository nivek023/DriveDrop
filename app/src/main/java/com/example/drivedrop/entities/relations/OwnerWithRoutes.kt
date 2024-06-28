package com.example.drivedrop.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.drivedrop.entities.Owner
import com.example.drivedrop.entities.Route

//represents the relation between an owner and a route. An Owner can possibly have more than one.
data class OwnerWithRoutes(
    @Embedded val owner: Owner,

    @Relation(
        parentColumn = "ownerId",
        entityColumn = "ownerId"
    )
    val routes: List<Route>
)