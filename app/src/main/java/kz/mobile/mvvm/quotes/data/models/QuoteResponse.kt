package kz.mobile.mvvm.quotes.data.models

data class QuoteResponse(
    val quoteText: String,
    val author: String
) {

    override fun toString(): String {
        return "$quoteText-$author"
    }
}
