package com.vholodynskyi.assignment.data.base.models

sealed class FetchType {
    object NetworkFetcher : FetchType()
    object MockFetcher : FetchType()
}