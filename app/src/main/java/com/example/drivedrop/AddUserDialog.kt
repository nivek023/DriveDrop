package com.example.drivedrop

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//part of the old database testing (first increment) overview - adding user & using composable
//defines logic of to add a new user - tracking events to route to next action

@Composable
fun AddUserDialog(
    state: UserState,
    onEvent: (UserEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = {
            onEvent(UserEvent.HideDialog)
        },
        title = {
            Text(text = "Add user")
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.firstName,
                    onValueChange = {
                        onEvent(UserEvent.SetFirstName(it))
                    },
                    placeholder = {
                        Text(text = "First name")
                    },
                    modifier = Modifier.fillMaxWidth()
                );
                TextField(
                    value = state.lastName,
                    onValueChange = {
                        onEvent(UserEvent.SetLastName(it))
                    },
                    placeholder = {
                        Text(text = "Last name")
                    },
                    modifier = Modifier.fillMaxWidth()
                );
                TextField(
                    value = state.email,
                    onValueChange = {
                        onEvent(UserEvent.SetEmail(it))
                    },
                    placeholder = {
                        Text(text = "email")
                    },
                    modifier = Modifier.fillMaxWidth()
                );
                TextField(
                    value = state.paymentInfo,
                    onValueChange = {
                        onEvent(UserEvent.SetPaymentInfo(it))
                    },
                    placeholder = {
                        Text(text = "Paymentinfo")
                    },
                    modifier = Modifier.fillMaxWidth()
                );
                TextField(
                    value = state.userName,
                    onValueChange = {
                        onEvent(UserEvent.SetUserName(it))
                    },
                    placeholder = {
                        Text(text = "Username")
                    },
                    modifier = Modifier.fillMaxWidth()
                );
                TextField(
                    value = state.password,
                    onValueChange = {
                        onEvent(UserEvent.SetPassword(it))
                    },
                    placeholder = {
                        Text(text = "Password")
                    },
                    modifier = Modifier.fillMaxWidth()
                );
                TextField(
                    value = state.bio,
                    onValueChange = {
                        onEvent(UserEvent.SetBio(it))
                    },
                    placeholder = {
                        Text(text = "Bio")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onEvent(UserEvent.SaveUser)
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onEvent(UserEvent.HideDialog)
                }
            ) {
                Text("Cancel")
            }
        },
        modifier = modifier
    )
}
