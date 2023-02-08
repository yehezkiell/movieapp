package com.movieapp.authentication

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.movieapp.authentication.model.AccountState
import com.tkpd.abstraction.ui.LoadingButton

@Composable
fun LoginScreen(viewModel: AccountViewModel = hiltViewModel()) {
    Surface {
        Column(
            modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Login")

            val loginState = viewModel.loginState.collectAsState()

            if (loginState.value is AccountState.Detail) {
                Toast.makeText(LocalContext.current, "Login Success", Toast.LENGTH_SHORT).show()
            }

            if (loginState.value is AccountState.Error.RequireFieldEmpty) {
                Toast.makeText(LocalContext.current, "Fill All Required Fields", Toast.LENGTH_SHORT)
                    .show()
            }

            if (loginState.value is AccountState.Error.FailLogin) {
                Toast.makeText(
                    LocalContext.current,
                    (loginState.value as AccountState.Error.FailLogin).message,
                    Toast.LENGTH_SHORT
                )
                    .show()
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
                loading = loginState.value is AccountState.Loading,
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

//            Button(
//                modifier = Modifier.padding(vertical = 16.dp),
//                onClick = {
//                    viewModel.doLogin(userName, password)
//                }) {
//                Icon(
//                    Icons.Filled.AccountBox,
//                    contentDescription = null
//                )
//                Spacer(modifier = Modifier.width(10.dp))
//                Text(text = "Login")
//            }
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