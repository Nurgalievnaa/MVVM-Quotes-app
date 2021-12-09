package kz.mobile.mvvm.di

import kz.mobile.mvvm.di.NetworkProperties.SERVER_URL
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal const val API_RETROFIT = "api_retrofit"
val networkModule = module {
    single { createOkHttpClient() }
    single(named(API_RETROFIT)) { createApiRetrofit(get(), SERVER_URL) }
}

object NetworkProperties {
    const val SERVER_URL = "https://api.themoviedb.org/"
}

fun createOkHttpClient(): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()

//    okHttpClientBuilder.addInterceptor { chain ->
//        var request = chain.request()
//        val url = request.url.newBuilder()
//        val requestBuilder = request.newBuilder()
//            .url(url.build())
//
//        chain.proceed(requestBuilder.build())
//    }
//    okHttpClientBuilder.retryOnConnectionFailure(true)

    return okHttpClientBuilder.build()
}

fun Scope.createApiRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}