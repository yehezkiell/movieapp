package com.tkpd.movieapp.feature.moviedetail

import android.content.Context
import android.content.Intent
import android.graphics.Paint.Align
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tkpd.movieapp.R
import com.tkpd.movieapp.constant.MovieConstant
import com.tkpd.movieapp.databinding.ActivityMovieDetailBinding
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

        val binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

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
            .replace(
                R.id.content_view,
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
