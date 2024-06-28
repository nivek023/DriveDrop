package com.example.drivedrop

import com.example.drivedrop.entities.User

// part of the old database testing (first increment) and representing user
data class UserState(
    val users: List<User> = emptyList(),
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val userName: String = "",
    val paymentInfo: String = "",
    val bio: String = "",

    val isAddingUser: Boolean = false,
    val sortType: SortType =SortType.FIRST_NAME
)
