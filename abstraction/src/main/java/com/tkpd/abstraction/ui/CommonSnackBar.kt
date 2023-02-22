package com.tkpd.abstraction.ui

import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect


@Composable
fun CommonSnackBar(
    snackbarHostState: SnackbarHostState,
    errorMessage: String,
    actionLabel: String,
    onDismissAction: () -> Unit,
    onActionPerformedAction: () -> Unit
) {
    LaunchedEffect(snackbarHostState) {
        // Show snackbar using a coroutine, when the coroutine is cancelled the
        // snackbar will automatically dismiss. This coroutine will cancel whenever
        // `showSnackBar` is false, and only start when `showSnackBar` is true
        // (due to the above if-check), or if `scaffoldState.snackbarHostState` changes.
        val result = snackbarHostState.showSnackbar(
            message = errorMessage,
            actionLabel = actionLabel
        )
        when (result) {
            SnackbarResult.Dismissed -> {
                onDismissAction()
            }
            SnackbarResult.ActionPerformed -> {
                onActionPerformedAction()
            }
        }
    }
}