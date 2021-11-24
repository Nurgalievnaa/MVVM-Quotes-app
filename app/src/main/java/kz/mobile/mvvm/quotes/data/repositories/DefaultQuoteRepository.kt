package kz.mobile.mvvm.quotes.data.repositories

import kz.mobile.mvvm.quotes.data.api.FakeQuoteDao
import kz.mobile.mvvm.quotes.data.mappers.QuoteMapper
import kz.mobile.mvvm.quotes.data.models.QuoteResponse
import kz.mobile.mvvm.quotes.domain.mappers.QuoteResponseMapper
import kz.mobile.mvvm.quotes.domain.models.Quote
import kz.mobile.mvvm.quotes.domain.repositories.QuoteRepository

class DefaultQuoteRepository(
    private val quoteDao: FakeQuoteDao,
    private val quoteMapper: QuoteMapper = QuoteMapper(),
    private val quoteResponseMapper: QuoteResponseMapper = QuoteResponseMapper()
) : QuoteRepository {

    override suspend fun addQuote(quote: Quote) {
        val quoteResponse: QuoteResponse = quoteResponseMapper.map(quote)
        quoteDao.addQuote(quoteResponse)
    }

    override suspend fun getQuotes(): List<Quote> {
        val quotesResponse: List<QuoteResponse> = quoteDao.getQuotes()
        val quotes: MutableList<Quote> = mutableListOf()
        for(item in quotesResponse){
            val quote: Quote = quoteMapper.map(item)
            quotes.add(quote)
        }

        return quotes
    }
}