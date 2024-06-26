package com.example.drivedrop.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.drivedrop.entities.Driver
import com.example.drivedrop.entities.Tour

data class DriverWithTours(
    @Embedded val driver: Driver,

    @Relation(
        parentColumn = "driverId",
        entityColumn = "driverId"
    )
    val tours: List<Tour>
)