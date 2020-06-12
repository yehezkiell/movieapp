package com.tkpd.movieapp.feature.movielist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tkpd.movieapp.R
import com.tkpd.movieapp.feature.movielist.view.MovieListFragment
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction()
            .replace(R.id.content_view, MovieListFragment())
            .commitAllowingStateLoss()
    }

}
