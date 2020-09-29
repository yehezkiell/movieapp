package com.tkpd.movieapp.feature.movielist

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.tkpd.movieapp.R
import com.tkpd.movieapp.constant.MovieConstant
import com.tkpd.movieapp.feature.movielist.view.MovieListFragment

import kotlinx.android.synthetic.main.activity_main.*

class MovieListActivity : AppCompatActivity() {

    private val darkModePref by lazy {
        this.getSharedPreferences(MovieConstant.DARK_MODE_PREF, MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));

        supportFragmentManager.beginTransaction()
            .replace(R.id.content_view, MovieListFragment())
            .commitAllowingStateLoss()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem: MenuItem = menu.findItem(R.id.menuShowDue)
        val textView = searchItem.actionView?.findViewById<TextView>(R.id.txt_menu)

        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                darkModePref.edit().putString(MovieConstant.DARK_MODE_KEY, MovieConstant.DARK_VALUE).apply()
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                darkModePref.edit().putString(MovieConstant.DARK_MODE_KEY, MovieConstant.LIGHT_VALUE).apply()
            }
        }

        textView?.apply {
            val mode = darkModePref.getString(MovieConstant.DARK_MODE_KEY, "")
            text = mode
            setOnClickListener {
                setDarkMode(this)
            }
        }

        return true
    }

    private fun setDarkMode(textView: TextView) {
        val editor = darkModePref.edit()
        when (textView.text) {
            MovieConstant.DARK_VALUE -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            MovieConstant.LIGHT_VALUE -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        editor.putString(MovieConstant.DARK_MODE_KEY, textView.text.toString())
        editor.apply()
    }
}
