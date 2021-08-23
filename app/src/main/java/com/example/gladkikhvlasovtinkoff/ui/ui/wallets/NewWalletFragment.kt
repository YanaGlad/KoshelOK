package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentNewWalletBinding
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

        configureToolbar()

        walletDataSample.currency.code = "rub"
        walletDataSample.currency.name = "Russian ruble"

        walletDataSample.limit = 0

        initLayout()
        setupFragmentNavigation()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setupFragmentNavigation() {
        binding.nameView.setOnClickListener {
        }

        binding.currencyView.setOnClickListener {
            val action =
                NewWalletFragmentDirections.actionNewWalletFragmentToCurrencyChoiceFragment(walletDataSample)
            findNavController().navigate(action)
        }

        binding.limitView.setOnClickListener {
            val action = NewWalletFragmentDirections.actionNewWalletFragmentToLimitFragment(walletDataSample)
            findNavController().navigate(action)
        }

        binding.buttonConfirm.setOnClickListener {
            val action = NewWalletFragmentDirections.actionNewWalletFragmentToOptionFragment()
            findNavController().navigate(action)
        }
    }

    private fun initLayout() {

        binding.name.attributeName.text = getString(R.string.wallet_name_title)
        binding.name.attributeValue.text = walletDataSample.name //TODO get name from parcel

        binding.currency.attributeName.text = getString(R.string.currency)
        binding.currency.attributeValue.text =
            walletDataSample.currency.name.toString() //TODO default value + currency choice


        binding.limit.attributeName.text = getString(R.string.limit)
        binding.limit.attributeValue.text =
            if (walletDataSample.limit != 0L) walletDataSample.limit.toString() else getString(
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