package kz.mobile.mvvm.quotes.data.api

import kz.mobile.mvvm.quotes.data.models.QuoteResponse

class FakeQuoteDao {
    // in real projects, retrofit apiservice
    private var quoteList = mutableListOf<QuoteResponse>()

    fun addQuote(quoteResponse: QuoteResponse) {
        quoteList.add(quoteResponse)
    }

    fun getQuotes() = quoteList as List<QuoteResponse>
}