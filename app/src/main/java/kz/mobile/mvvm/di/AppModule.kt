package kz.mobile.mvvm.quotes.di

import kz.mobile.mvvm.di.networkModule
import kz.mobile.mvvm.movies.di.movieModules
import kz.mobile.mvvm.users.di.userModules

val appModules = listOf(quoteModules, userModules, networkModule, movieModules)