package com.tkpd.movieapp.feature.movielist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.tkpd.movieapp.R
import com.tkpd.movieapp.feature.moviedetail.MovieDetailActivity
import com.tkpd.movieapp.feature.movielist.MovieListViewModelFactory
import com.tkpd.movieapp.feature.movielist.adapter.MovieAdapter
import com.tkpd.movieapp.model.MovieItem
import com.tkpd.movieapp.util.doSuccessOrFail
import com.tkpd.movieapp.util.hide
import com.tkpd.movieapp.util.show
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.item_error_view.*
import kotlinx.android.synthetic.main.item_loading_view.*


/**
 * Created by Yehezkiel on 17/05/20
 */
class MovieListFragment : Fragment(), MovieListListener {

    private var viewModel: MovieListViewModel? = null
    private val adapter: MovieAdapter by lazy {
        MovieAdapter(this)
    }
    private var dummyData: MutableList<MovieItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, MovieListViewModelFactory(requireContext()))
            .get(MovieListViewModel::class.java)

    }

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
    }

    private fun initRecyclerView() {
        rv_movie_list.layoutManager = GridLayoutManager(context, 3)
        rv_movie_list.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel?.topRatedMovies?.observe(viewLifecycleOwner, Observer { data ->
            data.doSuccessOrFail({
                dummyData = it.data?.movieItems?.toMutableList()
                adapter.setMovieList(dummyData)
            }, {

            }, ::showLoading, ::hideLoading)
        })
    }

    override fun onClick(id: Int) {
        val intent = MovieDetailActivity.createIntent(requireContext(), id)
        startActivity(intent)
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