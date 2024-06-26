package com.example.drivedrop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drivedrop.entities.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModel(
    private val dao: UserDAO
):ViewModel() {
    private val _sortType = MutableStateFlow(SortType.FIRST_NAME)
    private val _users = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                SortType.FIRST_NAME -> dao.getUserOrderedByFirstName()
                SortType.USER_NAME -> dao.getUserOrderedByUsername()
                SortType.EMAIL -> dao.getUserOrderedByEmail()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(UserState())
    val state = combine(_state, _sortType, _users) {state, sortType, users ->
        state.copy (
            users = users,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserState())

    fun onEvent(event: UserEvent) {
        when (event) {
            is UserEvent.DeleteUser -> {
                viewModelScope.launch() {
                    dao.deleteUser(event.user)
                }
            }
            UserEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingUser = false
                ) }
            }
            UserEvent.SaveUser -> {

                val firstName = state.value.firstName
                val lastName = state.value.lastName
                val email = state.value.email
                val password = state.value.password
                val userName = state.value.userName
                val paymentInfo = state.value.paymentInfo
                val bio = state.value.bio

                if(firstName.isBlank() || lastName.isBlank() || email.isBlank() ||
                    password.isBlank() || userName.isBlank() || paymentInfo.isBlank() ||
                    bio.isBlank()) {
                    return
                }

                val user = User(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password,
                    userName = userName,
                    paymentInfo = paymentInfo,
                    bio = bio
                )
                viewModelScope.launch {
                    dao.upsertUser(user)
                }
                _state.update { it.copy(
                    isAddingUser = false,
                    firstName = "",
                    lastName = "",
                    email = "",
                    password = "",
                    userName = "",
                    paymentInfo = "",
                    bio = ""
                ) }
            }
            is UserEvent.SetBio -> {
                _state.update { it.copy(
                    bio = event.bio
                )
                }
            }
            is UserEvent.SetEmail -> {
                _state.update { it.copy(
                    email = event.email
                )
                }
            }
            is UserEvent.SetFirstName -> {
                _state.update { it.copy(
                    firstName = event.firstName
                )
                }
            }
            is UserEvent.SetLastName -> {
                _state.update { it.copy(
                    lastName = event.lastName
                )
                }
            }
            is UserEvent.SetPassword -> {
                _state.update { it.copy(
                    password = event.password
                )
                }
            }
            is UserEvent.SetPaymentInfo -> {
                _state.update { it.copy(
                    paymentInfo = event.paymentInfo
                )
                }
            }
            is UserEvent.SetUserName -> {
                _state.update { it.copy(
                    userName = event.userName
                )
                }
            }
            UserEvent.ShowDialog ->{
                _state.update { it.copy(
                   isAddingUser = true
                )
                }
            }

            is UserEvent.SortUsers -> {
                _sortType.value =event.sortType
            }
        }
    }
}