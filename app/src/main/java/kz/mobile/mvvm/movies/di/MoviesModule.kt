package kz.mobile.mvvm.movies.di

import kz.mobile.mvvm.di.API_RETROFIT
import kz.mobile.mvvm.movies.data.api.ApiService
import kz.mobile.mvvm.movies.data.repositories.DefaultMovieRepository
import kz.mobile.mvvm.movies.domain.repositories.MovieRepository
import kz.mobile.mvvm.movies.domain.useCase.GetMoviesUseCase
import kz.mobile.mvvm.movies.domain.useCase.GetMoviesUseCaseImplementation
import kz.mobile.mvvm.movies.presentation.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val movieModules: Module = module {
    viewModel {
        MoviesViewModel(
            getMoviesUseCase = get()
        )
    }
    factory {
        DefaultMovieRepository(
            apiService = get()
        ) as MovieRepository
    }
    factory{
        GetMoviesUseCaseImplementation(
            movieRepository = get()
        ) as GetMoviesUseCase
    }
    factory {
        val retrofit: Retrofit = get(named(API_RETROFIT))
        retrofit.create(ApiService::class.java)
    }
}