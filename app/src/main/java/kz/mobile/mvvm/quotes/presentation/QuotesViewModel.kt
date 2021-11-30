package kz.mobile.mvvm.quotes.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kz.mobile.mvvm.quotes.domain.models.Quote
import kz.mobile.mvvm.quotes.domain.repositories.QuoteRepository

class QuotesViewModel(
    private val quoteRepository: QuoteRepository
) : ViewModel() {

    val quotesLiveData = MutableLiveData<List<Quote>>()

    fun getQuotesLiveData(): LiveData<List<Quote>> = quotesLiveData

    fun getQuotes() {
        CoroutineScope(Dispatchers.IO).launch {
            val quotesResponse: List<Quote> = quoteRepository.getQuotes()
            withContext(Dispatchers.Main) {
                quotesLiveData.value = quotesResponse
            }
        }

    }

    fun addQuote(quote: Quote) {
        Log.d("Akma",quote.toString())
        CoroutineScope(Dispatchers.IO).launch {
            quoteRepository.addQuote(quote)
        }
    }
}