package com.tkpd.movieapp.feature.moviedetail

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tkpd.movieapp.R
import com.tkpd.movieapp.constant.MovieConstant
import com.tkpd.movieapp.feature.moviedetail.view.MovieDetailFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_movie_detail.*

/**
 * Created by Yehezkiel on 29/05/20
 */
class MovieDetailActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context, movieId: Int) =
            Intent(context, MovieDetailActivity::class.java).putExtra(
                MovieConstant.PARAM_MOVIE_ID, movieId
            )
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
        val movieId = intent?.getIntExtra(MovieConstant.PARAM_MOVIE_ID, 0) ?: 0

        supportFragmentManager.beginTransaction()
            .replace(R.id.content_view,
                MovieDetailFragment.getFragment(
                    movieId
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
