package com.tkpd.abstraction.session

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSession @Inject constructor(@ApplicationContext private val applicationContext: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "usersession")
    private val SESSION_ID = stringPreferencesKey("session_id")


    fun getSessionId(): Flow<String> {
        return applicationContext.dataStore.data
            .map { preferences ->
                // No type safety.
                preferences[SESSION_ID] ?: ""
            }
    }

    suspend fun setSessionId(sessionId: String) {
        applicationContext.dataStore.edit {
            it[SESSION_ID] = sessionId
        }
    }

}