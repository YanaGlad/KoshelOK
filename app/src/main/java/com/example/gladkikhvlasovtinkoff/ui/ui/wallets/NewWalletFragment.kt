package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentEnterWalletNameBinding
import com.example.gladkikhvlasovtinkoff.databinding.FragmentNewWalletBinding
import com.example.gladkikhvlasovtinkoff.databinding.FragmentWalletsBinding
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder

class NewWalletFragment : ToolbarFragment() {

    private var _binding: FragmentNewWalletBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewWalletBinding.inflate(inflater)

        configureToolbar()
        initLayout()

        binding.nameView.setOnClickListener {

        }

        binding.currencyView.setOnClickListener {
            val action = NewWalletFragmentDirections.actionNewWalletFragmentToCurrencyChoiceFragment()
            findNavController().navigate(action)
        }

        binding.limitView.setOnClickListener {

        }

        binding.buttonConfirm.setOnClickListener {
            val action = NewWalletFragmentDirections.actionNewWalletFragmentToOptionFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun initLayout() {
        binding.name.attributeName.text = getString(R.string.wallet_name_title)
        binding.name.attributeValue.text = "----" //TODO get name from parcel

        binding.currency.attributeName.text = getString(R.string.currency)
        binding.currency.attributeValue.text = "----" //TODO default value + currency choice

        binding.limit.attributeName.text = getString(R.string.limit)
        binding.limit.attributeValue.text = "----" //TODO не установлен + edit
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun configureToolbar() {
        activity?.let { activity ->
            (activity as ToolbarHolder).setToolbarTitle(getString(R.string.new_wallet))
        }
    }
}