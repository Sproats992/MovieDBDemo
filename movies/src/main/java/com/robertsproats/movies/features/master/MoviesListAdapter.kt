package com.robertsproats.movies.features.master

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robertsproats.movies.R
import com.robertsproats.movies.model.MoviePresentationItemModel
import com.robertsproats.movies.util.ImageLoader
import kotlinx.android.synthetic.main.recyclerview_item_row.view.*

class MoviesListAdapter(private val context: Context,
                        private val listener: MovieClickedListener) :
        RecyclerView.Adapter<MoviesListAdapter.ViewHolder>() {

    private var moviesList: Array<MoviePresentationItemModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_row, parent, false))
    }

    override fun getItemCount(): Int {
        return moviesList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        moviesList?.apply {
            ImageLoader.loadImage(context, this[position].backdropPath, holder.itemImage)
            holder.itemTitle?.text = this[position].title
            holder.itemReleaseDate?.text = context.getString(R.string.released_on,
                    this[position].releaseDate)
            holder.itemPopularity?.text = context.getString(R.string.popularity,
                    this[position].voteAverage.toString())
            holder.itemView.setOnClickListener {
                listener.onItemClicked(position, this[position].id.toString())
            }
        }
    }

    fun updateList(movies: Array<MoviePresentationItemModel>?) {
        moviesList = movies
        this.notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage = view.itemImage
        val itemTitle = view.itemTitle
        val itemPopularity = view.itemPopularity
        val itemReleaseDate = view.itemReleaseDate
    }

}