package com.movieapp.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.movieapp.authentication.model.ResourceState
import com.tkpd.abstraction.extension.createImageUrl
import com.tkpd.abstraction.ui.CommonSnackBar
import com.tkpd.abstraction.ui.LoadingButton
import com.tkpd.abstraction.util.ComposeUtil.ImageBuilder
import tkpd.application.util.LocalSnackbarHostState

@Composable
fun AccountScreen(viewModel: AccountViewModel = hiltViewModel(), addCounter: () -> Unit) {
    val isLoggedIn = viewModel.isLoggedIn.collectAsState().value

    Surface {
        val loginState = viewModel.loginState.collectAsState()

        if (isLoggedIn) {
            AccountDetailScreen(
                loginState.value.data.avatar.tmdb.avatarPath,
                loginState.value.data.userName
            )
        } else {
            LoginScreen(viewModel) {
            }
        }
    }
}

@Composable
fun LoginScreen(viewModel: AccountViewModel, addCounter: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Login")

            val loginState = viewModel.loginState.collectAsState()

            val snackbarHostState = LocalSnackbarHostState.current
            if (loginState.value.error != null) {
                CommonSnackBar(snackbarHostState = snackbarHostState,
                    errorMessage = loginState.value.error ?: "",
                    actionLabel = "Dismiss",
                    onDismissAction = {
                        viewModel.resetErrorMessage()
                    },
                    onActionPerformedAction = {
                        viewModel.resetErrorMessage()
                    }
                )
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

@Composable
fun AccountDetailScreen(imageUrl: String, userName: String) {
    Column(
        modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val imageModifier =
            Modifier.size(64.dp).clip(CircleShape).border(2.dp, Color.Gray, CircleShape)
        if (imageUrl.isEmpty()) {
            Image(
                painter = painterResource(R.drawable.default_avatar),
                contentDescription = "",
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )
        } else {
            ImageBuilder(
                modifier = imageModifier,
                url = imageUrl.createImageUrl(),
                ContentScale.Crop
            )
        }

        Text(modifier = Modifier.padding(16.dp), text = userName)
    }
}

@Preview
@Composable
fun AccountScreenPreview() {
    Surface {
        AccountDetailScreen("", "Yehezkiel")
    }
}