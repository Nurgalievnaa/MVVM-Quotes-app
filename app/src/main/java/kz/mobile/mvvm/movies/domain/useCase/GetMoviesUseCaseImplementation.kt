package kz.mobile.mvvm.movies.domain.useCase

import kz.mobile.mvvm.movies.domain.models.Result
import kz.mobile.mvvm.movies.domain.presenters.MoviesPresenter
import kz.mobile.mvvm.movies.domain.repositories.MovieRepository

class GetMoviesUseCaseImplementation(val movieRepository: MovieRepository): GetMoviesUseCase {
    override fun getMovies(presenter: MoviesPresenter) {
        return movieRepository.getMovies(presenter)
    }
}