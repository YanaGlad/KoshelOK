package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentNewWalletBinding
import com.example.gladkikhvlasovtinkoff.extension.convertToStyled
import com.example.gladkikhvlasovtinkoff.model.WalletDataSample
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder

class NewWalletFragment : ToolbarFragment() {

    private var _binding: FragmentNewWalletBinding? = null
    private val binding get() = _binding!!

    private lateinit var walletDataSample: WalletDataSample
    private val args: NewWalletFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.walletDataSample.let { data ->
            walletDataSample = data
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewWalletBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureToolbar()
        initLayout()
        setupFragmentNavigation()
    }

    private fun setupFragmentNavigation() {
        binding.nameView.setOnClickListener {
            val action =
                NewWalletFragmentDirections.actionNewWalletFragmentToEnterWalletNameFragment(
                    walletDataSample
                )
            findNavController().navigate(action)
        }

        binding.currencyView.setOnClickListener {
            val action =
                NewWalletFragmentDirections.actionNewWalletFragmentToCurrencyChoiceFragment(
                    walletDataSample
                )
            findNavController().navigate(action)
        }

        binding.limitView.setOnClickListener {
            val action =
                NewWalletFragmentDirections.actionNewWalletFragmentToLimitFragment(walletDataSample)
            findNavController().navigate(action)
        }

        binding.buttonConfirm.setOnClickListener {
            val action = NewWalletFragmentDirections.actionNewWalletFragmentToWalletsFragment(args.walletDataSample)
            findNavController().navigate(action)
        }
    }

    private fun initLayout() {

        binding.name.attributeName.text = getString(R.string.wallet_name_title)
        binding.name.attributeValue.text = walletDataSample.name

        binding.currency.attributeName.text = getString(R.string.currency)
        binding.currency.attributeValue.text =
            if (walletDataSample.currency.name != "") walletDataSample.currency.name else getString(
                R.string.choose_currency
            )

        binding.limit.attributeName.text = getString(R.string.limit)
        binding.limit.attributeValue.text =
            if (walletDataSample.limit  != "") walletDataSample.limit
                .convertToStyled() else getString(
                R.string.not_setup
            )

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun configureToolbar() {
        activity?.let { activity ->
            val holder = activity as ToolbarHolder
            holder.setToolbarTitle(getString(R.string.new_wallet))
            holder.setToolbarNavigationButtonIcon(R.drawable.ic_back_arrow)
        }
    }
}