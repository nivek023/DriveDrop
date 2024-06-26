package com.example.drivedrop.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.drivedrop.entities.Chat
import com.example.drivedrop.entities.Driver
import com.example.drivedrop.entities.Owner
import com.example.drivedrop.entities.Tour

data class ChatWithOwnerDriverAndTour(
    @Embedded val chat: Chat,

    @Relation(
        parentColumn = "ownerId",
        entityColumn = "ownerId"
    )
    val owner: Owner,

    @Relation(
        parentColumn = "driverId",
        entityColumn = "driverId"
    )
    val driver: Driver,

    @Relation(
        parentColumn = "tourId",
        entityColumn = "tourId"
    )
    val tour: Tour
)