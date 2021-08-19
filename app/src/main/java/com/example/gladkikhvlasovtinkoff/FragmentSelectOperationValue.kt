package com.example.gladkikhvlasovtinkoff

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.databinding.FragmentSelectOperationValueBinding
import com.example.gladkikhvlasovtinkoff.extension.setDisabled
import com.example.gladkikhvlasovtinkoff.extension.setEnabled

class FragmentSelectOperationValue : Fragment() {

    private var _binding: FragmentSelectOperationValueBinding? = null
    private val binding get() = _binding!!

    val args: FragmentSelectOperationValueArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectOperationValueBinding.inflate(inflater)

        binding.buttonConfirmOperationValue.setOnClickListener {
            val operationData = args.operationData
            operationData.value = binding.newOperationValueField.text.toString()
            val action =
                FragmentSelectOperationValueDirections.actionFragmentSelectOperationValueToFragmentSelectOperationType(
                    operationData
                )

            findNavController().navigate(action)
        }

        binding.newOperationValueField.inputType =
            InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL

        binding.newOperationValueField.doOnTextChanged { _, _, _, _ ->
            val isEnabled = binding.newOperationValueField.text.toString() != ""
            if (isEnabled) {
                binding.buttonConfirmOperationValue.setEnabled(context)
            } else
                binding.buttonConfirmOperationValue.setDisabled(context)
            binding.buttonConfirmOperationValue.isEnabled = isEnabled
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        (activity as MainActivity?)
            ?.setActionBarTitle(resources.getString(R.string.enter_sum))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}