package com.vholodynskyi.assignment.data.feature.userContacts.usecase

import com.vholodynskyi.assignment.data.database.feature.contacts.model.DbContact
import com.vholodynskyi.assignment.data.feature.userContacts.repository.ContactOperationRepository


class GetUserContactByIdFromDBUseCase(
    private val repository: ContactOperationRepository
) {

    operator fun invoke(id: Int): DbContact = repository.getSelectedContactData(id.toLong())

}