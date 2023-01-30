package com.vholodynskyi.assignment.data.feature.userContacts.usecase

import com.vholodynskyi.assignment.data.feature.userContacts.repository.ContactOperationRepository


class DeleteContactFromDBUseCase(
    private val repository: ContactOperationRepository
) {

    suspend operator fun invoke(id: Int) = repository.deleteContact(id)

}