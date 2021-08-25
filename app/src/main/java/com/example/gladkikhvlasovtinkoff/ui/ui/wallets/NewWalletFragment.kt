package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentNewWalletBinding
import com.example.gladkikhvlasovtinkoff.extension.convertToStyled
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.WalletDataSample
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewWalletFragment : ToolbarFragment() {
    private val viewModel: NewWalletFragmentViewModel by viewModels()

    private var _binding: FragmentNewWalletBinding? = null
    private val binding get() = _binding!!

    private lateinit var walletDataSample: WalletDataSample
    private val args: NewWalletFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.walletDataSample?.let { data ->
            if(!data.currency.isSetup)
                setupStandardCurrency(data)
            walletDataSample = data
        }
    }

    private fun setupStandardCurrency(data: WalletDataSample) {
        context?.let { context ->
            data.currency = Currency(
                code = context.getString(R.string.standard_currency_code),
                name = context.getString(R.string.standard_currency_name)
            )
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
                    walletDataSample, true
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
                NewWalletFragmentDirections.actionNewWalletFragmentToLimitFragment(
                    walletDataSample,
                    args.isEdit
                )
            findNavController().navigate(action)
        }

        binding.buttonConfirm.setOnClickListener {
            if (!args.isEdit) {
                val action =
                    NewWalletFragmentDirections.actionNewWalletFragmentToWalletsFragment(args.walletDataSample)
                findNavController().navigate(action)
            } else {
                viewModel.updateWallet(walletDataSample)
                val action = NewWalletFragmentDirections.actionNewWalletFragmentToOptionFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun initLayout() {
        if (args.isEdit)
            binding.buttonConfirm.text = getString(R.string.save)
        binding.name.attributeName.text = getString(R.string.wallet_name_title)
        binding.name.attributeValue.text = walletDataSample.name
        binding.currency.attributeName.text = getString(R.string.currency)
        binding.currency.attributeValue.text =
            if (walletDataSample.currency.name != "") walletDataSample.currency.name else getString(
                R.string.choose_currency
            )
        binding.limit.attributeName.text = getString(R.string.limit)
        binding.limit.attributeValue.text =
            if (walletDataSample.limit != "") walletDataSample.limit
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