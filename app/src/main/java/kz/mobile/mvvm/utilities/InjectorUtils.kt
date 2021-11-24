package kz.mobile.mvvm.utilities

import kz.mobile.mvvm.quotes.data.api.FakeDatabase
import kz.mobile.mvvm.quotes.data.repositories.DefaultQuoteRepository
import kz.mobile.mvvm.quotes.presentation.QuotesViewModelFactory

object InjectorUtils {

    // This will be called from QuotesActivity
    fun provideQuotesViewModelFactory(): QuotesViewModelFactory {
        // ViewModelFactory needs a repository, which in turn needs a DAO from a database
        // The whole dependency tree is constructed right here, in one place
        val quoteRepository = DefaultQuoteRepository.getInstance(FakeDatabase.getInstance().quoteDao)
        return QuotesViewModelFactory(quoteRepository)
    }
}