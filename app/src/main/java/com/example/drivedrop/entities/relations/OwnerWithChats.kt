package com.example.drivedrop.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.drivedrop.entities.Owner
import com.example.drivedrop.entities.Chat

data class OwnerWithChats(
    @Embedded val owner: Owner,

    @Relation(
        parentColumn = "ownerId",
        entityColumn = "ownerId"
    )
    val chats: List<Chat>
)