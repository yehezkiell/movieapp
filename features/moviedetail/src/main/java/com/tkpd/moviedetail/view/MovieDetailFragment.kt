package com.tkpd.moviedetail.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tkpd.abstraction.constant.MovieConstant
import com.tkpd.abstraction.data.MovieDetail
import com.tkpd.abstraction.extension.*
import com.tkpd.abstraction.util.getErrorLayout
import com.tkpd.abstraction.util.getLoadingLayout
import com.tkpd.moviedetail.MovieDetailActivity
import com.tkpd.moviedetail.R
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject


/**
 * Created by Yehezkiel on 29/05/20
 */
class MovieDetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MovieDetailViewModel> { viewModelFactory }

    private var movieId: Int? = null

    companion object {
        fun getFragment(movieId: Int) = MovieDetailFragment().also {
            it.arguments = Bundle().apply {
                putInt(MovieConstant.PARAM_MOVIE_ID, movieId)
            }
        }
    }

    override fun onAttach(context: Context) {
        (activity as MovieDetailActivity).getComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getInt(MovieConstant.PARAM_MOVIE_ID, 0)
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
        showLoading()
        getErrorLayout.hide()
        viewModel.getMovieList(movieId ?: 0)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.movieDetail.observe(viewLifecycleOwner, Observer { data ->
            data.doSuccessOrFail({
                getErrorLayout.hide()
                renderView(it.data ?: MovieDetail())
            }, {
                getErrorLayout.show()
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
        img_banner?.setOnClickListener {
            val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.add(
                    R.id.content_view,
                    getFragment(
                        734309
                    )
                )
                ?.commit()
            fragmentTransaction?.addToBackStack("")
        }
    }

    private fun showLoading() {
        getErrorLayout.hide()
        getLoadingLayout.show()
    }

    private fun hideLoading() {
        getErrorLayout.hide()
        getLoadingLayout.hide()
    }
}