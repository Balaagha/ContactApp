package com.vholodynskyi.assignment.ui.contactslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vholodynskyi.assignment.data.base.models.DataWrapper
import com.vholodynskyi.assignment.data.feature.userContacts.usecase.FetchUserContactsDataUseCase
import com.vholodynskyi.assignment.framework.BaseViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ContactsListViewModel(
    val fetchUserContactsDataUseCase: FetchUserContactsDataUseCase
) : BaseViewModel() {

    fun fetchUserData() {
        viewModelScope.launch {
            fetchUserContactsDataUseCase.invoke().collectLatest {
                if (it is DataWrapper.Success) {
                    Log.d("myTag", "it.invoke => ${it.invoke()}")
                }
            }
        }
    }

}
