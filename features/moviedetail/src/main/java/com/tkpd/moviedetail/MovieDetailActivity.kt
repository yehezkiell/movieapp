package com.tkpd.moviedetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tkpd.abstraction.constant.MovieConstant
import com.tkpd.moviedetail.di.MovieDetailProvider
import com.tkpd.moviedetail.view.MovieDetailFragment
import kotlinx.android.synthetic.main.activity_movie_detail.*

/**
 * Created by Yehezkiel on 29/05/20
 * deeplink : tkpd://movieapp/moviedetail/{movie_id}
 */

class MovieDetailActivity : AppCompatActivity() {

    val getComponent by lazy {
        (application as MovieDetailProvider).provideMovieDetailComponent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setupToolbar()
        setupFragment()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setupFragment() {
        val movieId = intent?.data?.lastPathSegment ?: ""

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.content_view,
                MovieDetailFragment.getFragment(
                    movieId.toInt()
                )
            )
            .commitAllowingStateLoss()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        appbar.bringToFront()

        supportActionBar?.setDisplayShowTitleEnabled(false);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
