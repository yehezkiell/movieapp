package com.movieapp.authentication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.movieapp.authentication.model.ResourceState
import com.tkpd.abstraction.ui.LoadingButton
import tkpd.application.util.LocalSnackbarHostState

@Composable
fun LoginScreen(viewModel: AccountViewModel = hiltViewModel()) {
    Surface {
        Column(
            modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Login")
            val snackbarHostState = LocalSnackbarHostState.current
            snackbarHostState.currentSnackbarData

            val loginState = viewModel.loginState.collectAsState()

            if (loginState.value.error != null) {
                LaunchedEffect(snackbarHostState) {
                    // Show snackbar using a coroutine, when the coroutine is cancelled the
                    // snackbar will automatically dismiss. This coroutine will cancel whenever
                    // `showSnackBar` is false, and only start when `showSnackBar` is true
                    // (due to the above if-check), or if `scaffoldState.snackbarHostState` changes.
                    val result = snackbarHostState.showSnackbar(
                        message = loginState.value.error!!,
                        actionLabel = "Dismiss"
                    )
                    when (result) {
                        SnackbarResult.Dismissed -> {
                            viewModel.resetErrorMessage()
                        }
                        SnackbarResult.ActionPerformed -> {
                            viewModel.resetErrorMessage()
                        }
                    }
                }
            }

            var userName by rememberSaveable { mutableStateOf("") }

            OutlinedTextField(value = userName,
                label = {
                    Text("Username")
                },
                maxLines = 2,
                onValueChange = {
                    userName = it
                })

            var password by remember {
                mutableStateOf("")
            }

            OutlinedTextField(
                value = password,
                label = {
                    Text("Password")
                },
                maxLines = 2,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = {
                    password = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            LoadingButton(
                onClick = {
                    viewModel.doLogin(userName, password)
                },
                modifier = Modifier.padding(vertical = 16.dp),
                loading = loginState.value.state == ResourceState.LOADING,
                content = {
                    Row {
                        Icon(
                            Icons.Filled.AccountBox,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Login")
                    }
                }
            )

        }
    }
}

@Preview
@Composable
fun AccountScreenPreview() {
    Surface {
        LoginScreen()
    }
}