package com.tkpd.movielist.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.tkpd.abstraction.data.MovieItem
import com.tkpd.abstraction.extension.Result
import com.tkpd.abstraction.extension.doSuccessOrFail
import com.tkpd.abstraction.extension.hide
import com.tkpd.abstraction.extension.show
import com.tkpd.abstraction.util.ComposeUtil
import com.tkpd.abstraction.util.getErrorLayout
import com.tkpd.abstraction.util.getLoadingLayout
import com.tkpd.movielist.R
import com.tkpd.movielist.SharedPreferenceManager
import com.tkpd.movielist.adapter.MovieAdapter
import com.tkpd.movielist.di.MovieListProvider
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject


/**
 * Created by Yehezkiel on 17/05/20
 */
class MovieListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MovieListViewModel> { viewModelFactory }

    @Inject
    lateinit var sharedPref: SharedPreferenceManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.applicationContext as MovieListProvider).provideMovieListComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MovieListMainView(viewModel)
            }
        }
    }

    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun goToMovieDetail(id: String) {
        val url = Uri.parse("tkpd://movieapp/moviedetail").buildUpon().apply {
            appendPath(id)
        }.build().toString()
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    @Composable
    fun MovieListMainView(viewModel: MovieListViewModel) {
        val movieList by viewModel.topRatedMovies.observeAsState()

        when (movieList) {
            is Result.Success -> {
                MovieListLayout().MovieGridLayout(
                    movies = (movieList as Result.Success).data!!.movieItems,
                    onItemClick = {
                        goToMovieDetail(it)
                    })
            }
            is Result.Loading -> {
                ComposeUtil.LoadingView()
            }
            else -> {
                ComposeUtil.ErrorView()
            }
        }
    }
}