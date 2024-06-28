package com.example.drivedrop.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.drivedrop.entities.Tour
import com.example.drivedrop.entities.Car

//represents the relation from car and tours. Tours can take part multiple times.
data class CarWithTours (
    @Embedded val car: Car,
    @Relation(
        parentColumn = "carId",
        entityColumn = "carId"
    )
    val tours: List<Tour>,
)