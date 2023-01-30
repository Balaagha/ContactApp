package com.vholodynskyi.assignment.ui.contactslist

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vholodynskyi.assignment.data.base.models.DataWrapper
import com.vholodynskyi.assignment.data.feature.userContacts.usecase.FetchUserContactsDataAndSaveDBUseCase
import com.vholodynskyi.assignment.data.feature.userContacts.usecase.GetUserFromDBUseCase
import com.vholodynskyi.assignment.framework.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ContactsListViewModel(
    private val fetchUserContactsDataUseCase: FetchUserContactsDataAndSaveDBUseCase,
    private val getUserFromDBUseCase: GetUserFromDBUseCase
) : BaseViewModel() {

    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun fetchUserData(isRefresh: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchUserContactsDataUseCase.invoke(isRefresh).collectLatest {
                _isLoading.postValue(it is DataWrapper.Loading)
            }
        }
    }

    fun getUserContactFromDb() = getUserFromDBUseCase.invoke()

}
