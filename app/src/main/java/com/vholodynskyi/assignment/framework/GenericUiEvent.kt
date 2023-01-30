package com.vholodynskyi.assignment.framework

import androidx.annotation.StringRes

sealed class GenericUiEvent {
    class SnackBar(val title: String? = null) : GenericUiEvent()
    class Toast(val title: String? = null) : GenericUiEvent()
    class Alert(
        val title: String? = null,
        val message: String? = null,
        @StringRes val titleRes: Int? = null,
        @StringRes val messageRes: Int? = null
    ) : GenericUiEvent()
}
