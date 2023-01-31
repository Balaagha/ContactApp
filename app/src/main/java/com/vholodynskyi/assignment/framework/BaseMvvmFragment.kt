package com.vholodynskyi.assignment.framework

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.vholodynskyi.assignment.utils.extentions.observe

abstract class BaseMvvmFragment<VB : ViewBinding, VM : BaseViewModel>(
    private val bindingInflater: (LayoutInflater) -> VB
) : BaseFragment<VB>(bindingInflater) {

    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        // Listen to events
        observe(viewModel.singleTimeUiEvent, ::handleGenericsUiActionEvents)
    }

    protected open fun handleGenericsUiActionEvents(uiActionEvent: GenericUiEvent?) {
        when (uiActionEvent) {
            is GenericUiEvent.Alert -> {
                showAlertViaBaseUiEvent(uiActionEvent)
            }
            is GenericUiEvent.SnackBar -> {
                showSnackBarViaBaseUiEvent(uiActionEvent)
            }
            is GenericUiEvent.Toast -> {
                showToastViaBaseUiEvent(uiActionEvent)
            }
            else -> {
                Log.e(BaseViewModel.TAG, "Unknown event handle $uiActionEvent ")
            }
        }
    }

    protected open fun showToastViaBaseUiEvent(uiActionEvent: GenericUiEvent.Toast) {
        Toast.makeText(requireContext(), uiActionEvent.title ?: EMPTY, Toast.LENGTH_SHORT).show()
    }

    protected open fun showSnackBarViaBaseUiEvent(uiActionEvent: GenericUiEvent.SnackBar) {
        // No Implement yet
    }

    protected open fun showAlertViaBaseUiEvent(uiActionEvent: GenericUiEvent.Alert) {
        // No Implement yet
    }

    companion object {
        private const val EMPTY = ""
    }

}
