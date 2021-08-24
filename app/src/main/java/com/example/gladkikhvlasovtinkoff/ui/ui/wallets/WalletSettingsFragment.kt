package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentNewWalletBinding
import com.example.gladkikhvlasovtinkoff.databinding.FragmentWalletSettingsBinding


class WalletSettingsFragment : Fragment() {
    private var _binding: FragmentNewWalletBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewWalletBinding.inflate(layoutInflater)
        return binding.root
    }

}