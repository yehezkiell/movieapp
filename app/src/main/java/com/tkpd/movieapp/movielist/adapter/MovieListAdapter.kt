package com.tkpd.movieapp.movielist.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.movieapp.model.MovieItem
import com.tkpd.movieapp.movielist.view.MovieClickListener
import com.tkpd.movieapp.movielist.view.MovieListFragment
import com.tkpd.movieapp.movielist.viewholder.MovieItemViewHolder

/**
 * Created by Yehezkiel on 17/05/20
 */
class MovieListAdapter(val listener: MovieClickListener) :
    ListAdapter<MovieItem, MovieItemViewHolder>(MovieListDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    internal class MovieListDiffUtilCallBack : DiffUtil.ItemCallback<MovieItem>() {

        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem.title == newItem.title
        }

    }
}
