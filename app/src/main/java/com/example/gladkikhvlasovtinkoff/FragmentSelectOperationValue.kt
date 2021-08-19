package com.example.gladkikhvlasovtinkoff

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gladkikhvlasovtinkoff.databinding.FragmentSelectOperationValueBinding

class FragmentSelectOperationValue : Fragment() {

    private var _binding: FragmentSelectOperationValueBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectOperationValueBinding.inflate(inflater)

        binding.layoutMyToolbar.text.text = getResources().getString(R.string.enter_sum)

        binding.buttonConfirmOperationValue.setOnClickListener {
            val action =
                FragmentSelectOperationValueDirections.actionFragmentSelectOperationValueToFragmentSelectOperationType()
            findNavController().navigate(action)
        }

        binding.newOperationValueField.setInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL)

        binding.newOperationValueField.doOnTextChanged { text, start, before, count ->
            binding.buttonConfirmOperationValue.isEnabled = binding.newOperationValueField.text.toString() != ""
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}