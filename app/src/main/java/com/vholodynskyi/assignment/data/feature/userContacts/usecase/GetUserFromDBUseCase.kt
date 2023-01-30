package com.vholodynskyi.assignment.data.feature.userContacts.usecase

import com.vholodynskyi.assignment.data.database.feature.contacts.model.DbContact
import com.vholodynskyi.assignment.data.feature.userContacts.repository.ContactOperationRepository
import kotlinx.coroutines.flow.Flow


class GetUserFromDBUseCase(
    private val repository: ContactOperationRepository
) {

    operator fun invoke(): Flow<List<DbContact>> = repository.getAllContactsData()

}