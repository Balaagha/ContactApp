package com.vholodynskyi.assignment.ui.details

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vholodynskyi.assignment.databinding.FragmentDetailsBinding
import com.vholodynskyi.assignment.di.GlobalFactory
import com.vholodynskyi.assignment.framework.BaseMvvmFragment
import com.vholodynskyi.assignment.utils.extentions.observe


class DetailsFragment: BaseMvvmFragment<FragmentDetailsBinding, DetailsViewModel>(
    FragmentDetailsBinding::inflate,
) {

    private val args: DetailsFragmentArgs by navArgs()

    private val contactId by lazy {
        args.id
    }

    override val viewModel: DetailsViewModel by viewModels<DetailsViewModel> { GlobalFactory }

    override fun setup() {
        viewModel.getContactDataById(contactId).also{
            Log.d("myTag","it => $it")
        }
        binding.btnDelete.setOnClickListener {
            viewModel.deleteContact(contactId)
        }
        observe(viewModel.userContactData) { data ->
            binding.apply {
                tvFirstName.text = data?.firstName
                tvLastName.text = data?.lastName
                tvEmail.text = data?.email
            }
        }
        observe(viewModel.isDeleted) { value ->
            if(value == true) {
                findNavController().navigateUp()
            }
        }
    }


}