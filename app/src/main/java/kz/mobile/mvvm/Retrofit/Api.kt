package kz.mobile.mvvm.Retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("repositories")
    fun getDataFromAPI(@Query("q") query: String): Call<RecyclerList>

}