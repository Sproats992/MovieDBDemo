package com.robertsproats.movies.features.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.robertsproats.movies.R
import com.robertsproats.movies.model.MovieDetailPresentationModel
import com.robertsproats.movies.util.ImageLoader
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.movie_detail_fragment.*
import timber.log.Timber
import javax.inject.Inject

class MovieDetailFragment : Fragment() {

    lateinit var viewModel: MovieDetailViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var movieId: String? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MovieDetailViewModel::class.java]
        return inflater.inflate(R.layout.movie_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.arguments?.let {

            movieId = it.get("movie_id") as String?
            movieId?.let {
                showProgress()
                viewModel.movieDetailLiveData.observe(this.viewLifecycleOwner,
                        Observer {
                            hideProgress()
                            update(it)
                        })

                // kick off
                viewModel.fetchMovieDetail(it)

            }
        } ?: run {
            Timber.e("++++ no bundle detected?")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
    }

    fun update(movie: MovieDetailPresentationModel) {
        this.context?.let {
            ImageLoader.loadImage(it, movie.posterPath, moviePosterImage)
        }
        movieTitle.text = movie.title
        movieDescription.text = movie.overview
    }

    fun hideProgress() {
        progressContainer.visibility = View.GONE
        detailsContainer.visibility = View.VISIBLE
    }

    fun showProgress() {
        progressContainer.visibility = View.VISIBLE
        detailsContainer.visibility = View.GONE
    }
}
