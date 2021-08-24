package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentEnterWalletNameBinding
import com.example.gladkikhvlasovtinkoff.extension.observeTextChanged
import com.example.gladkikhvlasovtinkoff.extension.setDisabled
import com.example.gladkikhvlasovtinkoff.extension.setEnabled
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionSample
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder
import com.example.gladkikhvlasovtinkoff.ui.ui.transtaction.WalletTransactionFragmentArgs


class EnterWalletNameFragment : ToolbarFragment() {
    private var _binding: FragmentEnterWalletNameBinding? = null
    private val binding get() = _binding!!

    private val args: EnterWalletNameFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterWalletNameBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureToolbar()
        initLayout()
        setupNavigation()
    }

    private fun setupNavigation() {
        binding.layoutEnter.buttonConfirmOperationValue.setOnClickListener {
            val walletDataSample = args.walletDataSample
            walletDataSample.name = binding.layoutEnter.newOperationValueField.text.toString()
            val action =
                EnterWalletNameFragmentDirections.actionEnterWalletNameFragmentToNewWalletFragment(
                    walletDataSample, args.isEdit
                )
            findNavController().navigate(action)
        }
    }

    override fun configureToolbar() {
        setHasOptionsMenu(true)
        activity?.let { activity ->
            (activity as ToolbarHolder).setToolbarTitle(getString(R.string.setup_wallet_name))
        }
    }

    private fun initLayout() {
        binding.layoutEnter.newOperationValueField.setText(args.walletDataSample.name)
        binding.layoutEnter.newOperationValueBlock.hint = getString(R.string.wallet_name_entry)
        binding.layoutEnter.newOperationValueField.observeTextChanged(binding.layoutEnter.buttonConfirmOperationValue)
    }
}