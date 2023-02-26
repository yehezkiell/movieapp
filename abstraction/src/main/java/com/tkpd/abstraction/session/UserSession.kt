package com.tkpd.abstraction.session

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSession @Inject constructor(@ApplicationContext private val applicationContext: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "usersession")
    private val SESSION_ID = stringPreferencesKey("session_id")

    val sessionId = MutableStateFlow("")
    fun getSessionId(): Flow<String> {
        return applicationContext.dataStore.data
            .map { preferences ->
                // No type safety.
                preferences[SESSION_ID] ?: ""
            }
    }

    fun getSessionIdBlocking() = runBlocking {
        getSessionId().first()
    }

    suspend fun setSessionId(sessionId: String) {
        applicationContext.dataStore.edit {
            it[SESSION_ID] = sessionId
        }
    }

    suspend fun clearSessionId() {
        applicationContext.dataStore.edit {
            it.remove(SESSION_ID)
        }
    }

}