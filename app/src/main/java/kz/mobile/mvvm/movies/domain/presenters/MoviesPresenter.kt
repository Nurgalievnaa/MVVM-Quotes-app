package kz.mobile.mvvm.movies.domain.presenters

import androidx.annotation.WorkerThread
import kz.mobile.mvvm.movies.domain.models.Result

interface MoviesPresenter {

    fun onMoviesSuccessFetched(movies: List<Result>)
    fun onMoviesFetchError()
}