package com.tkpd.movieapp.feature.moviedetail.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tkpd.movieapp.R
import com.tkpd.movieapp.constant.MovieConstant
import com.tkpd.movieapp.constant.MovieConstant.PARAM_MOVIE_ID
import com.tkpd.movieapp.model.MovieDetail
import com.tkpd.movieapp.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.item_error_view.*
import javax.inject.Inject

/**
 * Created by Yehezkiel on 29/05/20
 */
@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    @Inject
    lateinit var movieDetailViewModelFactory: MovieDetailViewModel.AssistedFactory

    private val viewModel: MovieDetailViewModel by viewModels {
        MovieDetailViewModel.provideFactory(movieDetailViewModelFactory, movieId)
    }

    private var movieId: Int = 0

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
            movieId = it.getInt(PARAM_MOVIE_ID, 0) ?: 0
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress_bar_container.show()
        error_view.hide()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.movieDetail.observe(viewLifecycleOwner, Observer { data ->
            data?.doSuccessOrFail({
                error_view.hide()
                renderView(it.data ?: MovieDetail())
            }, {
                error_view.show()
            })
            progress_bar_container.hide()
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
}