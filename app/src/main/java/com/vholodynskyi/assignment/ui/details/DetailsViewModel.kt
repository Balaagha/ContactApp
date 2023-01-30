package com.vholodynskyi.assignment.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vholodynskyi.assignment.data.database.feature.contacts.model.DbContact
import com.vholodynskyi.assignment.data.feature.userContacts.usecase.DeleteContactFromDBUseCase
import com.vholodynskyi.assignment.data.feature.userContacts.usecase.GetUserContactByIdFromDBUseCase
import com.vholodynskyi.assignment.framework.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getUserContactByIdFromDBUseCase: GetUserContactByIdFromDBUseCase,
    private val deleteContactFromDBUseCase: DeleteContactFromDBUseCase
) : BaseViewModel() {

    private var _userContactData: MutableLiveData<DbContact> = MutableLiveData<DbContact>()
    val userContactData: LiveData<DbContact> get() = _userContactData

    private var _isDeleted: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val isDeleted: LiveData<Boolean> get() = _isDeleted


    fun getContactDataById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = getUserContactByIdFromDBUseCase.invoke(id)
            _userContactData.postValue(data)
        }
    }

    fun deleteContact(contactId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteContactFromDBUseCase.invoke(contactId)
            _isDeleted.postValue(true)
        }
    }

}
