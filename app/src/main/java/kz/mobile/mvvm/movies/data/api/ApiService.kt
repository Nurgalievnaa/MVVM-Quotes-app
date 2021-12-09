package kz.mobile.mvvm.movies.data.api

import kz.mobile.mvvm.movies.domain.models.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular")
    fun getMovies(@Query("api_key") key: String): Call<Movie>
}