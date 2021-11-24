package kz.mobile.mvvm.quotes.data.mappers

import kz.mobile.mvvm.quotes.data.models.QuoteResponse
import kz.mobile.mvvm.quotes.domain.models.Quote

class QuoteMapper {
    fun map(quoteResponse: QuoteResponse) : Quote {
        return Quote(
            quoteText = quoteResponse.quoteText,
            author = quoteResponse.author
        )
    }
}