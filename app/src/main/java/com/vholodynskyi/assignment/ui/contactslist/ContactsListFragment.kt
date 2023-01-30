package com.vholodynskyi.assignment.ui.contactslist

import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vholodynskyi.assignment.databinding.FragmentContactsListBinding
import com.vholodynskyi.assignment.di.GlobalFactory
import com.vholodynskyi.assignment.framework.BaseMvvmFragment
import com.vholodynskyi.assignment.utils.extentions.observe
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ContactsListFragment: BaseMvvmFragment<FragmentContactsListBinding, ContactsListViewModel>(
    FragmentContactsListBinding::inflate,
) {

    private val contactAdapter: ContactAdapter by lazy {
        ContactAdapter(
            requireContext(),
            this::onContactClicked
        )
    }

    private fun onContactClicked(id: Int) {
        findNavController()
            .navigate(ContactsListFragmentDirections.actionContactListToDetails(id))
    }

    override val viewModel: ContactsListViewModel by viewModels { GlobalFactory  }

    override fun setup() {
        binding.apply {
            contactList.layoutManager = LinearLayoutManager(context)
            contactList.adapter = contactAdapter
            progressView.isVisible = false
            swView.setOnRefreshListener {
                swView.isRefreshing = false
                viewModel.fetchUserData(true)
            }
        }

        viewModel.fetchUserData()

        viewLifecycleOwner.lifecycle.coroutineScope.launch {
            viewModel.getUserContactFromDb().collectLatest {
                Log.d("myTag","in fragment get data => it.size => ${it.size}")
                contactAdapter.items = it
            }
        }

        observe(viewModel.isLoading) { value ->
            binding.apply {
                progressView.isVisible = value == true
            }
        }
    }

}