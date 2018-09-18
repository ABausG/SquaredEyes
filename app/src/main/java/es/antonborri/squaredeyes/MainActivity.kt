package es.antonborri.squaredeyes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import es.antonborri.squaredeyes.adapters.BackdropMovieAdapter
import es.antonborri.squaredeyes.adapters.BackdropShowAdapter
import es.antonborri.squaredeyes.viewmodel.HomeViewModel
import es.antonborri.squaredeyes.viewmodel.HomeViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory
    lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var popularMovieAdapter: BackdropMovieAdapter

    @Inject
    lateinit var trendingShowAdapter: BackdropShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

        viewModel.popularMovies.data().observe(this, Observer {
            popularMovieAdapter.submitList(it)
        })

        viewModel.trendingShows.data().observe(this, Observer {
            trendingShowAdapter.submitList(it)
        })

        rv_popular_movies.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
            adapter = popularMovieAdapter
        }

        rv_trending_shows.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
            adapter = trendingShowAdapter
        }
    }


}
