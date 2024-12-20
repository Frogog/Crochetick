package com.example.crochetick.instance

import com.example.crochetick.apiInterface.ApiInterface
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val httpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(AuthInterceptor())
        .build()

    val api: ApiInterface by lazy {
        Retrofit.Builder()
            //.baseUrl("http://192.168.0.2:8000")
            .baseUrl("http://192.168.0.5:8000")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}

class AuthInterceptor : Interceptor {

    companion object{
        var TOKEN = ""
    }

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val originalRequest = chain.request()

        // Если токен пустой, отправляем запрос без него
        if (TOKEN.isEmpty()) {
            return chain.proceed(originalRequest)
        }

        // Добавляем токен в заголовок
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer ${TOKEN}")  // Обычно используется Bearer токен
            // или .header("Authorization", TokenManager.token) // Если токен используется без Bearer
            .build()

        return chain.proceed(newRequest)
    }
}