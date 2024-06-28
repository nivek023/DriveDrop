package com.example.drivedrop.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.drivedrop.entities.Driver
import com.example.drivedrop.entities.Route

//represents relation between driver and routes. A driver can possible have multiple routes.
data class DriverWithRoutes(
    @Embedded val driver: Driver,

    @Relation(
        parentColumn = "driverId",
        entityColumn = "driverId"
    )
    val routes: List<Route>
)