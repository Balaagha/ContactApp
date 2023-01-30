package com.vholodynskyi.assignment.data.feature.userContacts.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiContactResponse(val results: List<ApiContact>?)