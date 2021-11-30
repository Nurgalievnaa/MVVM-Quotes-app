package kz.mobile.mvvm.quotes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import kz.mobile.mvvm.R
import kz.mobile.mvvm.quotes.domain.models.Quote
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.StringBuilder

private const val EMPTY_STRING = ""

class QuotesActivity : AppCompatActivity() {

    private val quotesViewModel: QuotesViewModel by viewModel()
    private lateinit var quotesTextView: TextView
    private lateinit var quoteAddButton: Button
    private lateinit var quoteEditText: EditText
    private lateinit var authorEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        observeViewModel()
        initClickListeners()
    }

    private fun initViews() {
        quotesTextView = findViewById(R.id.textView_quotes)
        quoteAddButton = findViewById(R.id.button_add_quote)
        quoteEditText = findViewById(R.id.editText_quote)
        authorEditText = findViewById(R.id.editText_author)
    }

    private fun initClickListeners() {
        quoteAddButton.setOnClickListener {
            addQuote()
            clearEditText()
            quotesViewModel.getQuotes()
        }
    }

    private fun addQuote() {
        val quoteText: String = quoteEditText.text.toString()
        val authorText: String = authorEditText.text.toString()
        val quote = Quote(
            quoteText = quoteText,
            author = authorText
        )
        quotesViewModel.addQuote(quote = quote)
    }

    private fun clearEditText(){
        quoteEditText.setText(EMPTY_STRING)
        authorEditText.setText(EMPTY_STRING)
    }


    private fun observeViewModel() {
        quotesViewModel.getQuotesLiveData().observe(this, Observer { quotes ->
            setQuoteText(quotes = quotes)
        })
    }

    private fun setQuoteText(quotes: List<Quote>) {
        val stringBuilder = StringBuilder()
        quotes.forEach { quote ->
            stringBuilder.append("${quote.quoteText} - ${quote.author}\n\n")
        }
        quotesTextView.text = stringBuilder.toString()
    }
}