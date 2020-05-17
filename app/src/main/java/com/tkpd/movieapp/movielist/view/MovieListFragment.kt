package com.tkpd.movieapp.movielist.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.tkpd.movieapp.R
import com.tkpd.movieapp.model.MovieItem
import com.tkpd.movieapp.movielist.adapter.MovieListAdapter
import com.tkpd.movieapp.util.ViewModelFactory
import com.tkpd.movieapp.util.doSuccessOrFail
import kotlinx.android.synthetic.main.fragment_movie_list.*

/**
 * Created by Yehezkiel on 17/05/20
 */
class MovieListFragment : Fragment(), MovieClickListener {

    private val viewModelFactory = ViewModelFactory()
    private val viewModel: MovieListViewModel by viewModels(factoryProducer = { viewModelFactory })
    private val adapter: MovieListAdapter by lazy {
        MovieListAdapter(this)
    }
    private var dummyData: MutableList<MovieItem>? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel.getMovieDetail()
    }

    private fun initRecyclerView() {
        rv_movie_list.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.topRatedMovies.observe(viewLifecycleOwner, Observer { data ->
            data.doSuccessOrFail({
                dummyData = it.data?.movieItems?.toMutableList()
                adapter.submitList(it.data?.movieItems?.toMutableList() ?: mutableListOf())
            }, {
                Log.e("asdnya", "error ${it.message}")
            })
        })
    }

    override fun onClick(id: Int) {
        dummyData?.removeAll {
            it.id == id
        }
        adapter.submitList(dummyData?.toMutableList() ?: mutableListOf())
    }
}

interface MovieClickListener {
    fun onClick(id: Int)
}