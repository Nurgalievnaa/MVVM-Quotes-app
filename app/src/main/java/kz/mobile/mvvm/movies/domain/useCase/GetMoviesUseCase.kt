package kz.mobile.mvvm.movies.domain.useCase

import androidx.annotation.WorkerThread
import kz.mobile.mvvm.movies.domain.models.Result
import kz.mobile.mvvm.movies.domain.presenters.MoviesPresenter

interface GetMoviesUseCase {
    @WorkerThread
    fun getMovies(presenter: MoviesPresenter)
}