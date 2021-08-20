package com.example.gladkikhvlasovtinkoff.ui.ui.transactionsum

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentSelectTransactionValueBinding
import com.example.gladkikhvlasovtinkoff.extension.*
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text

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

        binding.buttonConfirmOperationValue.setOnClickListener {
            val operationData = args.operationData
            operationData.amount = binding.newOperationValueField.text.toString()
            val action =
                FragmentSelectTransactionValueDirections.actionFragmentSelectOperationValueToFragmentSelectOperationType(
                    operationData
                )

            findNavController().navigate(action)
        }

        binding.newOperationValueField.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL

        binding.newOperationValueField.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                binding.newOperationValueField.removeTextChangedListener(this)
                val isEnabled = s.toString() != ""
                binding.newOperationValueField.setText(s?.toString()?.styleInput() ?: "")
                if (isEnabled) {
                    binding.buttonConfirmOperationValue.setEnabled(context)
                } else
                    binding.buttonConfirmOperationValue.setDisabled(context)
                binding.buttonConfirmOperationValue.isEnabled = isEnabled
                binding.newOperationValueField.setSelection(
                    binding.newOperationValueField.text?.toString()?.length ?: 0)
                binding.newOperationValueField.addTextChangedListener(this)
            }
        })

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
        activity?.let{activity ->
            (activity as ToolbarHolder).setToolbarTitle(getString(R.string.enter_sum))
        }
    }

}