package com.example.drivedrop

import com.example.drivedrop.entities.User

//part of the old user testing setup (first increment)
sealed interface UserEvent {
    object SaveUser : UserEvent
    data class SetFirstName(val firstName: String): UserEvent
    data class SetLastName(val lastName: String): UserEvent
    data class SetEmail(val email: String): UserEvent
    data class SetPassword(val password: String): UserEvent
    data class SetUserName(val userName: String): UserEvent
    data class SetPaymentInfo(val paymentInfo: String): UserEvent
    data class SetBio(val bio: String): UserEvent

    object ShowDialog: UserEvent
    object HideDialog: UserEvent
    data class SortUsers(val sortType: SortType): UserEvent
    data class DeleteUser(val user: User): UserEvent
}