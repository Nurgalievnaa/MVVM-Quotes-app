package kz.mobile.mvvm.quotes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kz.mobile.mvvm.quotes.data.repositories.DefaultQuoteRepository

class QuotesViewModelFactory(private val defaultQuoteRepository: DefaultQuoteRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(defaultQuoteRepository) as T
    }
}