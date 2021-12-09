package kz.mobile.mvvm.movies.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.mobile.mvvm.movies.domain.models.Result
import kz.mobile.mvvm.movies.domain.presenters.MoviesPresenter
import kz.mobile.mvvm.movies.domain.useCase.GetMoviesUseCase

class MoviesViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel(), MoviesPresenter {

    init {
        getMovies()
    }

    private val movieLiveData = MutableLiveData<List<Result>>()
    private val errorLiveData = MutableLiveData<Boolean>()

    override fun onMoviesSuccessFetched(movies: List<Result>) {
        movieLiveData.value = movies
    }

    override fun onMoviesFetchError() {
        errorLiveData.value = true
    }

    fun getMoviesLiveData(): LiveData<List<Result>> = movieLiveData

    fun getMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            getMoviesUseCase.getMovies(this@MoviesViewModel)
        }
    }
}