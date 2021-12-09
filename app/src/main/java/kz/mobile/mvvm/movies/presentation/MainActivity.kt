package kz.mobile.mvvm.movies.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.mobile.mvvm.R
import kz.mobile.mvvm.movies.MoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var progress_bar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var titleView: TextView
    private val moviesViewModel: MoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
       initViews()
        observeViewModel()
    }

    private fun initViews() {
        progress_bar = findViewById(R.id.progress_bar)
        recyclerView = findViewById(R.id.recyclerView)
        titleView = findViewById(R.id.title)
        titleView.setOnClickListener {
            moviesViewModel.getMovies()
        }
    }

    private fun observeViewModel() {
        moviesViewModel.getMoviesLiveData().observe(this, Observer { movies ->
            recyclerView.apply {
                progress_bar.visibility = View.GONE
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = MoviesAdapter(movies)
            }
        })
    }
}

