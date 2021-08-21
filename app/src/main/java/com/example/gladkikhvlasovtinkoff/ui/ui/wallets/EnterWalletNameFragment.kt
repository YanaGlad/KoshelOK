package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentEnterWalletNameBinding
import com.example.gladkikhvlasovtinkoff.extension.setDisabled
import com.example.gladkikhvlasovtinkoff.extension.setEnabled
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder


class EnterWalletNameFragment : ToolbarFragment() {
    private var _binding: FragmentEnterWalletNameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterWalletNameBinding.inflate(inflater)

        configureToolbar()
        initLayout()

        binding.layoutEnter.buttonConfirmOperationValue.setOnClickListener {
            val action =
                EnterWalletNameFragmentDirections.actionEnterWalletNameFragmentToNewWalletFragment()
            findNavController().navigate(action)
        }

        observeTextChanged()

        return binding.root
    }

    private fun observeTextChanged() {
        binding.layoutEnter.newOperationValueField.doOnTextChanged { _, _, _, _ ->
            val isEnabled = binding.layoutEnter.newOperationValueField.text.toString() != ""
            if (isEnabled) {
                binding.layoutEnter.buttonConfirmOperationValue.setEnabled(context)
            } else
                binding.layoutEnter.buttonConfirmOperationValue.setDisabled(context)
        }
    }

    override fun configureToolbar() {
        setHasOptionsMenu(true)
        activity?.let { activity ->
            (activity as ToolbarHolder).setToolbarTitle(getString(R.string.setup_wallet_name))
        }
    }

    private fun initLayout() {
        binding.layoutEnter.newOperationValueBlock.hint = getString(R.string.wallet_name_entry)
    }


}