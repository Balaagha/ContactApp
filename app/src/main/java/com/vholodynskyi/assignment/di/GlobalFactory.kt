package com.vholodynskyi.assignment.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.vholodynskyi.assignment.data.base.api.RetrofitServicesProvider
import com.vholodynskyi.assignment.data.feature.userContacts.service.ContactsService
import com.vholodynskyi.assignment.data.database.base.AppDatabase
import com.vholodynskyi.assignment.data.database.feature.contacts.dao.ContactsDao
import com.vholodynskyi.assignment.data.database.feature.contacts.source.ContactLocalDataSource
import com.vholodynskyi.assignment.data.database.feature.contacts.source.ContactLocalDataSourceImpl
import com.vholodynskyi.assignment.data.database.utils.DatabaseConstant.DATABASE_NAME
import com.vholodynskyi.assignment.data.feature.userContacts.repository.ContactOperationRepository
import com.vholodynskyi.assignment.data.feature.userContacts.repository.ContactOperationRepositoryImpl
import com.vholodynskyi.assignment.data.feature.userContacts.usecase.FetchUserContactsDataAndSaveDBUseCase
import com.vholodynskyi.assignment.data.feature.userContacts.usecase.GetUserFromDBUseCase
import com.vholodynskyi.assignment.ui.contactslist.ContactsListViewModel
import com.vholodynskyi.assignment.ui.details.DetailsViewModel

object GlobalFactory: ViewModelProvider.Factory {

    private lateinit var db: AppDatabase

    private val service: ContactsService by lazy {
        RetrofitServicesProvider().contactsService
    }

    private val contactDao: ContactsDao by lazy {
        db.userDao()
    }

    private val contactLocalDataSource: ContactLocalDataSource by lazy {
        ContactLocalDataSourceImpl(
            dao = contactDao
        )
    }

    private val contactOperationRepository: ContactOperationRepository by lazy {
        ContactOperationRepositoryImpl(
            dataSource = contactLocalDataSource,
            service = service
        )
    }

    private val fetchUserContactsDataUseCase by lazy {
        FetchUserContactsDataAndSaveDBUseCase(
            repository = contactOperationRepository
        )
    }

    private val getUserFromDBUseCase by lazy {
        GetUserFromDBUseCase(
            repository = contactOperationRepository
        )
    }

    fun init(context: Context) {
        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            ContactsListViewModel::class.java -> ContactsListViewModel(fetchUserContactsDataUseCase,getUserFromDBUseCase)
            DetailsViewModel::class.java -> DetailsViewModel()
            else -> throw IllegalArgumentException("Cannot create factory for ${modelClass.simpleName}")
        } as T
    }

}
