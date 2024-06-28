package com.example.drivedrop.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.drivedrop.entities.Driver
import com.example.drivedrop.entities.Chat

//represents the relation between driver and chats. Chats can take part multiple times.
data class DriverWithChats(
    @Embedded val driver: Driver,

    @Relation(
        parentColumn = "driverId",
        entityColumn = "driverId"
    )
    val chats: List<Chat>
)