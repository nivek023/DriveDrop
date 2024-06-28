package com.example.drivedrop.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.drivedrop.entities.Owner
import com.example.drivedrop.entities.Chat

//represents the relation between an Owner and chat. An owner can have more than one chat.
data class OwnerWithChats(
    @Embedded val owner: Owner,

    @Relation(
        parentColumn = "ownerId",
        entityColumn = "ownerId"
    )
    val chats: List<Chat>
)