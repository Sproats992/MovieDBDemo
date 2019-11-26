package com.robertsproats.movies.features.master

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.robertsproats.movies.R
import com.robertsproats.movies.model.MoviesPresentationModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.movies_fragment.*
import javax.inject.Inject

class MoviesFragment : Fragment(), MovieClickedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: MoviesViewModel

    var moviesListAdapter: MoviesListAdapter? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MoviesViewModel::class.java]
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            moviesListAdapter = MoviesListAdapter(it, this)
        }

        moviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            moviesListAdapter?.let {
                adapter = it
            }
        }

        showProgress()
        viewModel.moviesLiveData.observe(
                this.viewLifecycleOwner,
                Observer {
                    hideProgress()
                    updateList(it)
                })

        // kick off
        viewModel.fetchMovies()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
    }

    fun updateList(movies: MoviesPresentationModel) {
        moviesRecyclerView?.apply {
            moviesListAdapter?.let {
                it.updateList(movies.moviesList)
            }
        }
    }

    fun hideProgress() {
        progressContainer.visibility = View.GONE
        moviesRecyclerView.visibility = View.VISIBLE
    }

    fun showProgress() {
        progressContainer.visibility = View.VISIBLE
        moviesRecyclerView.visibility = View.GONE
    }

    override fun onItemClicked(item: Int, id: String) {
        findNavController().navigate(R.id.navigate_to_movie_detail, bundleOf(Pair("movie_id", id)))
    }
}
