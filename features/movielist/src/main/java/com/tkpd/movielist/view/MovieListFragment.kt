package com.tkpd.movielist.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tkpd.abstraction.extension.Result
import com.tkpd.abstraction.util.ComposeUtil
import com.tkpd.movielist.SharedPreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * Created by Yehezkiel on 17/05/20
 */
@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private val viewModel by viewModels<MovieListViewModel>()

    @Inject
    lateinit var sharedPref: SharedPreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MovieListMainView(viewModel)
            }
        }
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