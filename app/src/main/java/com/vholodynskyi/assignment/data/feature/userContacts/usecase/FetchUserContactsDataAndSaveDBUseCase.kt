package com.vholodynskyi.assignment.data.feature.userContacts.usecase

import android.util.Log
import com.vholodynskyi.assignment.data.base.models.DataWrapper
import com.vholodynskyi.assignment.data.base.models.FailureBehavior
import com.vholodynskyi.assignment.data.base.models.FailureType
import com.vholodynskyi.assignment.data.database.feature.contacts.model.DbContact
import com.vholodynskyi.assignment.data.feature.userContacts.models.ApiContactResponse
import com.vholodynskyi.assignment.data.feature.userContacts.repository.ContactOperationRepository
import com.vholodynskyi.assignment.utils.checkNetworkResultIsSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FetchUserContactsDataAndSaveDBUseCase(
    private val repository: ContactOperationRepository
) {
    var fetchingTime: Long = 0L

    suspend operator fun invoke(isRefresh: Boolean = false): Flow<DataWrapper<ApiContactResponse>> = flow {
        emit(DataWrapper.Loading)
        val currentTime = System.nanoTime()
        Log.d("myTag","in invoke fetchingTime => $fetchingTime | currentTime => ${currentTime} | REFRESH_STEP_TIME => ${REFRESH_STEP_TIME} " +
                " currentTime - fetchingTime => ${currentTime - fetchingTime}")
        if(isRefresh || fetchingTime == 0L || currentTime - fetchingTime > REFRESH_STEP_TIME) {
            fetchingTime = System.nanoTime()
            try {

                val response = repository.getContactsFromAPI()
                if (response is DataWrapper.Success<*> && checkNetworkResultIsSuccess(response.invoke())) {
                    response.invoke()?.body()?.let {
                        repository.clearDBWithFlagLogic()
                        val contactList: ArrayList<DbContact> = arrayListOf()
                        it.results?.forEach { contactData ->
                            if (repository.getAllContactsByFirstNameData(
                                    contactData.name?.firstName ?: ""
                                ).isEmpty()
                            ) {
                                contactList.add(
                                    DbContact(
                                        firstName = contactData.name?.firstName,
                                        lastName = contactData.name?.lastName,
                                        email = contactData.email,
                                        photo = contactData.picture?.medium
                                    )
                                )
                            }
                        }
                        if(contactList.isNotEmpty()){
                            repository.insertContactList(contactList)
                        }

                        emit(DataWrapper.Success(it))
                    } ?: kotlin.run {
                        emit(
                            DataWrapper.Failure<ApiContactResponse>(
                                FailureType.OTHER,
                                FailureBehavior.ALERT,
                                message = "Null Body"
                            )
                        )
                    }
                } else {
                    emit(
                        DataWrapper.Failure<ApiContactResponse>(
                            FailureType.API_GENERIC_ERROR,
                            FailureBehavior.ALERT,
                            message = "Error connection"
                        )
                    )
                }
            } catch (e: Exception) {
                emit(
                    DataWrapper.Failure<ApiContactResponse>(
                        FailureType.OTHER,
                        FailureBehavior.ALERT,
                        message = e.localizedMessage
                    )
                )
            }
        }

    }

    companion object{
        private const val REFRESH_STEP_TIME = 1 * 30 * 1000_000_000L
    }
}