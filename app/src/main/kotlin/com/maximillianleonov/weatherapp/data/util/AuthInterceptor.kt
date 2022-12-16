package com.maximillianleonov.weatherapp.data.util

import com.maximillianleonov.weatherapp.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val apiKeyProvider: ApiKeyProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url().newBuilder()
            .addQueryParameter(API_KEY_QUERY, apiKeyProvider.apiKey)
            .build()

        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }

    private companion object {
        private const val API_KEY_QUERY = Constants.Remote.API_KEY_QUERY
    }
}
