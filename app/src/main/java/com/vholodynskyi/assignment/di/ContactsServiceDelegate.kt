package com.vholodynskyi.assignment.di

import com.vholodynskyi.assignment.data.base.api.RetrofitServicesProvider
import com.vholodynskyi.assignment.data.feature.userContacts.service.ContactsService
import kotlin.properties.ReadOnlyProperty
import kotlin.random.Random
import kotlin.reflect.KProperty

class ContactsServiceDelegate : ReadOnlyProperty<Any, ContactsService> {

    override fun getValue(thisRef: Any, property: KProperty<*>): ContactsService {
        return RetrofitServicesProvider().contactsService
    }
}