package com.vholodynskyi.assignment.data.base.models


data class ApiErrorResponseModel(
    var error: String? = null,
    var errorDetails: String? = null,
)
