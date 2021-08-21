package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentSelectTransactionValueBinding
import com.example.gladkikhvlasovtinkoff.extension.setDisabled
import com.example.gladkikhvlasovtinkoff.extension.setEnabled
import com.example.gladkikhvlasovtinkoff.extension.setupTextStyleAndObserve
import com.example.gladkikhvlasovtinkoff.extension.styleInput
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder
import com.google.android.material.textfield.TextInputEditText
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

        binding.layoutEnter.buttonConfirmOperationValue
            .setOnClickListener {
                val operationData = args.operationData
                operationData.amount = binding.layoutEnter.newOperationValueField.text.toString()
                val action =
                    FragmentSelectTransactionValueDirections.actionFragmentSelectOperationValueToFragmentSelectOperationType(
                        operationData
                    )

                findNavController().navigate(action)
            }

        binding.layoutEnter.newOperationValueField.inputType =
            InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL

        setupTextStyleAndObserve(binding.layoutEnter.newOperationValueField, binding.layoutEnter.buttonConfirmOperationValue)

        return binding.root
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