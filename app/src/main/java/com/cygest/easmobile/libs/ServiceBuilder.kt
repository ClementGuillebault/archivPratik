package com.cygest.easmobile.libs

import com.cygest.easmobile.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//object ServiceBuilder {
//    private val client = OkHttpClient.Builder().build()
//
//    private val retrofit = Retrofit.Builder()
//            .baseUrl(Constants.URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//
//    private fun addInterceptor(interceptor: AuthInterceptor) {
//        client.interceptors().add(interceptor)
//    }
//
//    fun<T> buildService(service: Class<T>): T {
//        return retrofit.create(service)
//    }
//}

class HttpBuilder {
    fun<T> build(service: Class<T>): T {
        val client = OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()
        return Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(service)
    }
}

class AuthInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder.addHeader("Authorization", "Bearer " + Constants.TOKEN)
//        CacheMemory.getToken(context).let {
//            requestBuilder.addHeader("Authorization", "Bearer $it")
//        }

        return chain.proceed(requestBuilder.build())
    }
}