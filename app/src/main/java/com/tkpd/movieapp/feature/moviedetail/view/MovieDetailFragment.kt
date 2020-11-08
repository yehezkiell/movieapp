package com.tkpd.movieapp.feature.moviedetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tkpd.movieapp.R
import com.tkpd.movieapp.constant.MovieConstant
import com.tkpd.movieapp.constant.MovieConstant.PARAM_MOVIE_ID
import com.tkpd.movieapp.feature.moviedetail.MovieDetailViewModelFactory
import com.tkpd.movieapp.model.MovieDetail
import com.tkpd.movieapp.util.*
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.item_error_view.*
import kotlinx.android.synthetic.main.item_loading_view.*

/**
 * Created by Yehezkiel on 29/05/20
 */
class MovieDetailFragment : Fragment() {

    private var viewModel: MovieDetailViewModel? = null
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
            movieId = it.getInt(MovieConstant.PARAM_MOVIE_ID, 0)
        }

        viewModel = ViewModelProvider(this, MovieDetailViewModelFactory(requireActivity().applicationContext))
            .get(MovieDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
        error_view.hide()
        viewModel?.getMovieDetail(movieId ?: 0)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel?.movieDetail?.observe(viewLifecycleOwner, Observer { data ->
            data.doSuccessOrFail({
                error_view.hide()
                renderView(it.data ?: MovieDetail())
            }, {
                error_view.show()
            }, ::showLoading, ::hideLoading)
        })
    }

    private fun renderView(data: MovieDetail) {
        img_banner.loadImage(data.backdropPath)
        img_movie.loadImageRounded(data.posterPath)
        movie_detail_title.text = data.title
        movie_rating.text = data.voteAverage.toString()
        movie_popularity.text = data.popularity.toString()
        movie_release_date.text = data.releaseDate
        movie_description.text = data.overview
    }

    private fun showLoading() {
        error_view.hide()
        progress_bar_container.show()
    }

    private fun hideLoading() {
        error_view.hide()
        progress_bar_container.hide()
    }
}