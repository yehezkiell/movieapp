package com.tkpd.movielist

import android.content.Context
import com.tkpd.abstraction.di.ApplicationScope
import com.tkpd.movielist.view.MovieListFragment
import javax.inject.Inject

// This class only for testing purpose
// Make sure module get Application Context from Application
class SharedPreferenceManager @Inject constructor(@ApplicationScope val context: Context) {
    fun getSharedPref(key: String): String {
        val pref =
            context.getSharedPreferences(MovieListFragment::class.simpleName, Context.MODE_PRIVATE)
        return pref.getString(key, "") ?: ""
    }

    fun editPref(key: String, value: String) {
        val pref =
            context.getSharedPreferences(MovieListFragment::class.simpleName, Context.MODE_PRIVATE)
                .edit()
        pref.putString(key, value).apply()
    }
}