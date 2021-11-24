package kz.mobile.mvvm.quotes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kz.mobile.mvvm.R
import kz.mobile.mvvm.quotes.data.models.QuoteResponse
import kz.mobile.mvvm.utilities.InjectorUtils

class QuotesActivity : AppCompatActivity() {

    private lateinit var textView_quotes: TextView
    private lateinit var button_add_quote: Button
    private lateinit var editText_quote: EditText
    private lateinit var editText_author: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initializeUi()
        observeViewModel()
    }

    private fun initializeUi() {
        // Get the QuotesViewModelFactory with all of it's dependencies constructed
        val factory = InjectorUtils.provideQuotesViewModelFactory()
        // Use ViewModelProviders class to create / get already created QuotesViewModel
        // for this view (activity)
        val viewModel = ViewModelProviders.of(this, factory)
            .get(QuotesViewModel::class.java)

        // Observing LiveData from the QuotesViewModel which in turn observes
        // LiveData from the repository, which observes LiveData from the DAO ☺
        viewModel.getQuotes().observe(this, Observer { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach { quote ->
                stringBuilder.append("$quote\n\n")
            }
            textView_quotes.text = stringBuilder.toString()
        })

        // When button is clicked, instantiate a Quote and add it to DB through the ViewModel
        button_add_quote.setOnClickListener {
            val quote = QuoteResponse(editText_quote.text.toString(), editText_author.text.toString())
            viewModel.addQuote(quote)
            editText_quote.setText("")
            editText_author.setText("")
        }
    }

    private fun initViews() {
        textView_quotes = findViewById(R.id.textView_quotes)
        button_add_quote = findViewById(R.id.button_add_quote)
        editText_quote = findViewById(R.id.editText_quote)
        editText_author = findViewById(R.id.editText_author)
    }

    private fun observeViewModel() {


    }

}