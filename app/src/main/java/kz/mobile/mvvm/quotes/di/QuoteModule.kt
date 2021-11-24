package kz.mobile.mvvm.quotes.di

import kz.mobile.mvvm.quotes.data.api.FakeDatabase
import kz.mobile.mvvm.quotes.data.repositories.DefaultQuoteRepository
import kz.mobile.mvvm.quotes.domain.repositories.QuoteRepository
import kz.mobile.mvvm.quotes.presentation.QuotesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val quoteModules: Module = module {
    viewModel {
        QuotesViewModel(
            quoteRepository = get()
        )
    }
    factory {
        DefaultQuoteRepository(
            quoteDao = FakeDatabase.getInstance().quoteDao
        ) as QuoteRepository
    }
}
