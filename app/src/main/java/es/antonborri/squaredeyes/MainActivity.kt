package es.antonborri.squaredeyes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import es.antonborri.squaredeyes.adapters.BackdropMovieAdapter
import es.antonborri.squaredeyes.viewModel.HomeViewModel
import es.antonborri.squaredeyes.viewModel.HomeViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: HomeViewModelFactory
    lateinit var viewModel: HomeViewModel

    @Inject lateinit var popularMovieAdapter: BackdropMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

        viewModel.popularMovies().observe(this, Observer {
            popularMovieAdapter.submitList(it)
        })

        rv_popular.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
            adapter = popularMovieAdapter
        }
    }


}
