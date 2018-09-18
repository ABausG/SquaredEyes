package es.antonborri.squaredeyes.network

import es.antonborri.squaredeyes.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class TraktInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("trakt-api-version", "2")
                .addHeader("trakt-api-key", BuildConfig.TRAKT_CLIENT_ID)
                .build()


        return chain.proceed(request)
    }

}