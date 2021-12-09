package kz.mobile.mvvm.movies.domain.repositories

import androidx.annotation.WorkerThread
import kz.mobile.mvvm.movies.domain.models.Movie
import kz.mobile.mvvm.movies.domain.models.Result
import kz.mobile.mvvm.movies.domain.presenters.MoviesPresenter

interface MovieRepository {

    @WorkerThread
    fun getMovies(presenter: MoviesPresenter)
}