package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentSelectTransactionValueBinding
import com.example.gladkikhvlasovtinkoff.extension.convertFromStyled
import com.example.gladkikhvlasovtinkoff.extension.setupTextStyleAndObserve
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSelectTransactionValue : ToolbarFragment() {

    private var _binding: FragmentSelectTransactionValueBinding? = null
    private val binding get() = _binding!!

    private val args: FragmentSelectTransactionValueArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectTransactionValueBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUi()
        binding.layoutEnter.buttonConfirmOperationValue
            .setOnClickListener {
                onNextNavigate()
            }

        binding.layoutEnter.newOperationValueField.inputType =
            InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL

        binding.layoutEnter.newOperationValueField
            .setupTextStyleAndObserve(buttonObserver = binding.layoutEnter.buttonConfirmOperationValue)
        binding.layoutEnter.newOperationValueField
            .setupTextStyleAndObserve(buttonObserver = binding.layoutEnter.buttonConfirmOperationValue)
    }

    private fun setupUi() {
        binding.layoutEnter.newOperationValueBlock.hint = getString(R.string.operation_value_text)
    }

    private fun onNextNavigate() {
        val operationData = args.operationData
        operationData.amount =
            binding.layoutEnter.newOperationValueField.text.toString().convertFromStyled()

        val action: NavDirections = if (!operationData.isValid)
            FragmentSelectTransactionValueDirections
                .actionFragmentSelectOperationValueToFragmentSelectOperationType(operationData)
        else
            FragmentSelectTransactionValueDirections
                .actionFragmentSelectOperationValueToFragmentConfirmOperationCreating(operationData)
        findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        configureToolbar()
    }

    override fun configureToolbar() {
        activity?.let { activity ->
            (activity as ToolbarHolder).setToolbarTitle(getString(R.string.enter_sum))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
