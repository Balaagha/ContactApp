package com.vholodynskyi.assignment.data.feature.userContacts.usecase

import android.graphics.Bitmap
import com.vholodynskyi.assignment.data.base.models.DataWrapper
import com.vholodynskyi.assignment.data.base.models.FailureBehavior
import com.vholodynskyi.assignment.data.base.models.FailureType
import com.vholodynskyi.assignment.data.feature.userContacts.models.ApiContactResponse
import com.vholodynskyi.assignment.data.feature.userContacts.repository.ContactOperationRepository
import com.vholodynskyi.assignment.utils.checkNetworkResultIsSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FetchUserContactsDataUseCase (
    private val repository: ContactOperationRepository
) {
    suspend operator fun invoke(): Flow<DataWrapper<ApiContactResponse>> = flow {
        emit(DataWrapper.Loading)
        try {
            val response = repository.getContactsFromAPI()
            if (response is DataWrapper.Success<*> && checkNetworkResultIsSuccess(response.invoke())) {
                response.invoke()?.body()?.let {
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