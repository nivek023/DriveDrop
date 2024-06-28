package com.example.drivedrop.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.drivedrop.entities.Car
import com.example.drivedrop.entities.Driver
import com.example.drivedrop.entities.Route
import com.example.drivedrop.entities.Tour

// represents the relation of a tour that has a rout, a driver and a car.
data class TourWithRouteAndDriverAndCar(
    @Embedded val tour: Tour,

    @Relation(
        parentColumn = "driverId",
        entityColumn = "driverId"
    )
    val driver: Driver,

    @Relation(
        parentColumn = "routeId",
        entityColumn = "routeId"
    )
    val route: Route,

    @Relation(
        parentColumn = "carId",
        entityColumn = "carId"
    )
    val car : Car,
)