package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentEnterWalletNameBinding
import com.example.gladkikhvlasovtinkoff.databinding.FragmentNewWalletBinding
import com.example.gladkikhvlasovtinkoff.databinding.FragmentWalletsBinding

class NewWalletFragment : Fragment() {

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

        initLayout()

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
}