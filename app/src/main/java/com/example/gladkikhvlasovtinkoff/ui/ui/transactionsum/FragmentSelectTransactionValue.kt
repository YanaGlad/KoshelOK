package com.example.gladkikhvlasovtinkoff.ui.ui.transactionsum

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentSelectTransactionValueBinding
import com.example.gladkikhvlasovtinkoff.extension.setDisabled
import com.example.gladkikhvlasovtinkoff.extension.setEnabled
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

        initLayout()

        binding.layoutEnter.buttonConfirmOperationValue.setOnClickListener {
            val operationData = args.operationData
            operationData.value = binding.layoutEnter.newOperationValueField.text.toString()
            val action =
                FragmentSelectTransactionValueDirections.actionFragmentSelectOperationValueToFragmentSelectOperationType(
                    operationData
                )

            findNavController().navigate(action)
        }

        binding.layoutEnter.newOperationValueField.inputType =
            InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL

        binding.layoutEnter.newOperationValueField.doOnTextChanged { _, _, _, _ ->
            val isEnabled = binding.layoutEnter.newOperationValueField.text.toString() != ""
            if (isEnabled) {
                binding.layoutEnter.buttonConfirmOperationValue.setEnabled(context)
            } else
                binding.layoutEnter.buttonConfirmOperationValue.setDisabled(context)
            binding.layoutEnter.buttonConfirmOperationValue.isEnabled = isEnabled
        }

        return binding.root
    }

    private fun initLayout() {
        binding.layoutEnter.newOperationValueBlock.hint = getString(R.string.operation_value_text)
    }


    override fun onResume() {
        super.onResume()
        configureToolbar()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun configureToolbar() {
        activity?.let { activity ->
            (activity as ToolbarHolder).setToolbarTitle(getString(R.string.enter_sum))
        }
    }

}