package com.tkpd.abstraction.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkpd.abstraction.session.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ActivitySharedViewModel @Inject constructor(
    private val userSession: UserSession
) : ViewModel() {
    private val _isLoggedIn: MutableStateFlow<Boolean> =
        MutableStateFlow(userSession.getSessionIdBlocking().isNotEmpty())
    val isLoggedIn: StateFlow<Boolean>
        get() = _isLoggedIn

    init {
        viewModelScope.launch(Dispatchers.Main) {
            userSession.getSessionId().collect {
                _isLoggedIn.tryEmit(it.isNotEmpty())
            }
        }
    }
}