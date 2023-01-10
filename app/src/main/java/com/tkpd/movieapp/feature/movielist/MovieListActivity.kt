package com.tkpd.movieapp.feature.movielist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tkpd.movieapp.R
import com.tkpd.movieapp.databinding.ActivityMainBinding
import com.tkpd.movieapp.feature.movielist.view.MovieListFragment

import kotlinx.android.synthetic.main.activity_main.*

class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(toolbar)
        supportFragmentManager.beginTransaction()
            .replace(R.id.content_view, MovieListFragment())
            .commitAllowingStateLoss()
    }

}
