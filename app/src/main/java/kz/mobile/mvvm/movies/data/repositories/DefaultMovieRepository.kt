package kz.mobile.mvvm.movies.data.repositories

import android.util.Log
import kz.mobile.mvvm.movies.data.api.ApiService
import kz.mobile.mvvm.movies.domain.models.Movie
import kz.mobile.mvvm.movies.domain.models.Result
import kz.mobile.mvvm.movies.domain.presenters.MoviesPresenter
import kz.mobile.mvvm.movies.domain.repositories.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DefaultMovieRepository(
    private val apiService: ApiService
) : MovieRepository {
    override fun getMovies(presenter: MoviesPresenter) {
        val call = apiService.getMovies("29e4ba3b7ccc73f1db0d40b5df590ff4")
        var movieList: List<Result> = mutableListOf()

        call.enqueue(object : Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                movieList = response.body()?.results ?: listOf()
                presenter.onMoviesSuccessFetched(movieList)
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                presenter.onMoviesFetchError()
            }
        })
    }
}