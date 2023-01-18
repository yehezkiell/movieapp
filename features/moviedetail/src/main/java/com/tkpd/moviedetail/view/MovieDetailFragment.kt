package com.tkpd.moviedetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tkpd.abstraction.constant.MovieConstant.PARAM_MOVIE_ID
import com.tkpd.abstraction.util.ComposeUtil
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by Yehezkiel on 29/05/20
 */
@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var movieId: Int? = null

    companion object {
        fun getFragment(movieId: Int) = MovieDetailFragment().also {
            it.arguments = Bundle().apply {
                putInt(PARAM_MOVIE_ID, movieId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getInt(PARAM_MOVIE_ID, 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MovieDetailMainLayout()
            }
        }
    }

    @Composable
    fun MovieDetailMainLayout(viewModel: MovieDetailViewModel = viewModel()) {
        val showError by viewModel.showError.collectAsState()
        val showLoading by viewModel.showLoading.collectAsState()
        val movieDetail by viewModel.movieDetail.observeAsState()

        viewModel.getMovieList(movieId!!)

        if (showError) {
            ComposeUtil.ErrorView()
        } else if (showLoading || movieDetail == null) {
            ComposeUtil.LoadingView()
        } else {
            MovieDetailLayout().MovieDetailContent(movieDetail!!)
        }
    }
}