package com.example.drivedrop.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.drivedrop.entities.Driver
import com.example.drivedrop.entities.Owner
import com.example.drivedrop.entities.Route

data class RouteWithDriverAndOwner(
    @Embedded val route: Route,

    @Relation(
        parentColumn = "driverId",
        entityColumn = "driverId"
    )
    val driver: Driver,

    @Relation(
        parentColumn = "ownerId",
        entityColumn = "ownerId"
    )
    val owner: Owner
)
