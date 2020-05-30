package com.tkpd.movieapp.feature.movielist.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.tkpd.movieapp.MovieApplication
import com.tkpd.movieapp.R
import com.tkpd.movieapp.feature.moviedetail.MovieDetailActivity
import com.tkpd.movieapp.feature.moviedetail.view.MovieDetailViewModel
import com.tkpd.movieapp.model.MovieItem
import com.tkpd.movieapp.feature.movielist.adapter.MovieListAdapter
import com.tkpd.movieapp.util.doSuccessOrFail
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject

/**
 * Created by Yehezkiel on 17/05/20
 */
class MovieListFragment : DaggerFragment(), MovieClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MovieListViewModel> { viewModelFactory }


    private val adapter: MovieListAdapter by lazy {
        MovieListAdapter(this)
    }
    private var dummyData: MutableList<MovieItem>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel.getMovieList()
    }

    private fun initRecyclerView() {
        rv_movie_list.layoutManager = GridLayoutManager(context, 3)
        rv_movie_list.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.topRatedMovies.observe(viewLifecycleOwner, Observer { data ->
            data.doSuccessOrFail({
                dummyData = it.data?.movieItems?.toMutableList()
                adapter.submitList(it.data?.movieItems?.toMutableList() ?: mutableListOf())
            }, {

            })
        })
    }

    override fun onClick(id: Int) {
        //        dummyData?.removeAll {
        //            it.id == id
        //        }
        //        adapter.submitList(dummyData?.toMutableList() ?: mutableListOf())
        val intent = MovieDetailActivity.createIntent(requireContext(), id)
        startActivity(intent)
    }
}

interface MovieClickListener {
    fun onClick(id: Int)
}