package com.vholodynskyi.assignment.data.base.api

import com.vholodynskyi.assignment.data.feature.userContacts.service.ContactsService
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitServicesProvider {
    private val BASE_URL = "https://randomuser.me/"

    private val moshi: Moshi = Moshi.Builder()
        .build()

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient: OkHttpClient by lazy {
        val client = OkHttpClient.Builder()
        client.addInterceptor(httpLoggingInterceptor)

        client.readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)

        client.build()
    }
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()

    val contactsService: ContactsService
        get() = retrofit.create(ContactsService::class.java)
}