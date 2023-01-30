package com.vholodynskyi.assignment.data.feature.userContacts.service

import com.vholodynskyi.assignment.data.feature.userContacts.models.ApiContactResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ContactsService {
    @GET("api/")
    suspend fun getContacts(@Query("results") limit: Int = 30): Response<ApiContactResponse>
}