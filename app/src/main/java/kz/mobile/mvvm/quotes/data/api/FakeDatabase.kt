package kz.mobile.mvvm.quotes.data.api

class FakeDatabase private constructor() {

    var quoteDao = FakeQuoteDao()

    companion object {
        @Volatile private var instance: FakeDatabase? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: FakeDatabase().also { instance = it }
            }
    }
}