package com.vholodynskyi.assignment.ui.contactslist

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vholodynskyi.assignment.databinding.FragmentContactsListBinding
import com.vholodynskyi.assignment.di.GlobalFactory
import com.vholodynskyi.assignment.framework.BaseMvvmFragment

open class ContactsListFragment: BaseMvvmFragment<FragmentContactsListBinding, ContactsListViewModel>(
    FragmentContactsListBinding::inflate,
) {

    private val contactAdapter: ContactAdapter by lazy {
        ContactAdapter(
            requireContext(),
            this::onContactClicked
        )
    }

    private fun onContactClicked(id: String) {
        findNavController()
            .navigate(ContactsListFragmentDirections.actionContactListToDetails(id))
    }

    override val viewModel: ContactsListViewModel by viewModels { GlobalFactory  }

    override fun setup() {
        binding.apply {
            contactList.layoutManager = LinearLayoutManager(context)
            contactList.adapter = contactAdapter
        }
        viewModel.fetchUserData()
    }

}