package kz.mobile.mvvm.quotes.domain.mappers

import kz.mobile.mvvm.quotes.data.models.QuoteResponse
import kz.mobile.mvvm.quotes.domain.models.Quote

class QuoteResponseMapper {
    fun map(quote: Quote): QuoteResponse {
        return QuoteResponse(
            quoteText = quote.quoteText,
            author = quote.author
        )
    }
}