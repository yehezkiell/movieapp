package com.tkpd.movieapp.feature.movielist.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.movieapp.R
import com.tkpd.movieapp.model.MovieItem
import com.tkpd.movieapp.feature.movielist.view.MovieClickListener
import com.tkpd.movieapp.util.loadImageRounded
import kotlinx.android.synthetic.main.item_movie_list.view.*


/**
 * Created by Yehezkiel on 17/05/20
 */
class MovieItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        fun inflate(parent: ViewGroup): MovieItemViewHolder {
            return MovieItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_movie_list, parent, false)
            )
        }
    }

    fun bind(data: MovieItem, listener: MovieClickListener) = with(view) {
        view.setOnClickListener {
            listener.onClick(data.id)
        }
        movie_title.text = data.title
        movie_image.loadImageRounded(data.posterPath)
    }
}