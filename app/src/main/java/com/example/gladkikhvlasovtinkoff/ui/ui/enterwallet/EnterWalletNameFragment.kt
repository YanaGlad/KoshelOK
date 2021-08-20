package com.example.gladkikhvlasovtinkoff.ui.ui.enterwallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentEnterWalletNameBinding
import com.example.gladkikhvlasovtinkoff.databinding.FragmentWalletsBinding
import com.example.gladkikhvlasovtinkoff.extension.setDisabled
import com.example.gladkikhvlasovtinkoff.extension.setEnabled


class EnterWalletNameFragment : Fragment() {
    private var _binding: FragmentEnterWalletNameBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterWalletNameBinding.inflate(inflater)

        initLayout()

        binding.layoutEnter.buttonConfirmOperationValue.setOnClickListener {
            val action = EnterWalletNameFragmentDirections.actionEnterWalletNameFragmentToOptionFragment()
            findNavController().navigate(action)
        }

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
        binding.layoutEnter.newOperationValueBlock.hint = getString(R.string.wallet_name_entry)
    }


}