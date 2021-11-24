package kz.mobile.mvvm.quotes.domain.repositories

import kz.mobile.mvvm.quotes.domain.models.Quote

interface QuoteRepository {

    suspend fun getQuotes(): List<Quote>

    suspend fun addQuote(quote: Quote)
}