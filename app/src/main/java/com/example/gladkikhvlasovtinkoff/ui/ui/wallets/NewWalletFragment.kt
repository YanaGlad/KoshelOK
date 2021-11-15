package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.ErrorPresenter
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentNewWalletBinding
import com.example.gladkikhvlasovtinkoff.extension.convertToStyled
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.UNDEFINED_STR
import com.example.gladkikhvlasovtinkoff.model.WalletData.Companion.INFINITE_LIMIT
import com.example.gladkikhvlasovtinkoff.model.WalletDataSample
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder
import com.example.gladkikhvlasovtinkoff.ui.ui.viewmodel.NewWalletFragmentViewModel
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
        args.walletDataSample.let { data ->
            if (!data.currency.isSetup)
                setupStandardCurrency(data)
            if (data.limit == UNDEFINED_STR)
                data.limit = INFINITE_LIMIT
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
        configureConfirmButton()
        viewModel.viewState.observe(viewLifecycleOwner) {
            handleViewState(it)
        }
    }

    private fun configureConfirmButton() {
        binding.buttonConfirm.setOnClickListener {
            if (!args.isEdit)
                viewModel.addWallet(walletDataSample)
            else
                viewModel.updateWallet(walletDataSample)
        }
    }

    private fun handleViewState(viewState: NewWalletViewState) =
        when (viewState) {
            is NewWalletViewState.Loading -> setLoading()
            is NewWalletViewState.SuccessOperation -> onSuccess()
            is NewWalletViewState.Error.UnexpectedError -> onUnexpectedError()
            is NewWalletViewState.Error.NetworkError -> onNetworkError()
        }

    private fun onNetworkError() {
        setLoaded()
        activity?.let { activity ->
            (activity as ErrorPresenter).showNetworkError {
                if (!args.isEdit)
                    viewModel.addWallet(walletDataSample)
                else
                    viewModel.updateWallet(walletDataSample)
            }
        }
    }


    private fun onUnexpectedError() {
        activity?.let { activity ->
            (activity as ErrorPresenter).showUnexpectedError()
        }
        setLoaded()
    }

    private fun onSuccess() {
        setLoaded()
        if (!args.isEdit) {
            val action =
                NewWalletFragmentDirections.actionNewWalletFragmentToWalletsFragment()
            findNavController().navigate(action)
        } else {
            val action = NewWalletFragmentDirections.actionNewWalletFragmentToOptionFragment(args.walletData.toWalletDataSample())
            findNavController().navigate(action)
        }
    }


    private fun setLoading() {
        binding.buttonConfirm.text = ""
        binding.newWalletProgressBar.visibility = View.VISIBLE
    }

    private fun setLoaded() {
        binding.buttonConfirm.text =
            if (args.isEdit)
                getString(R.string.save)
            else getString(R.string.next_text)
        binding.newWalletProgressBar.visibility = View.GONE
    }

    private fun setupFragmentNavigation() {
        binding.nameView.setOnClickListener {
            val action =
                NewWalletFragmentDirections.actionNewWalletFragmentToEnterWalletNameFragment(
                    walletDataSample, args.isEdit
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
                viewModel.addWallet(walletDataSample)
            } else {
                viewModel.updateWallet(walletDataSample)
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
            if (walletDataSample.limit != INFINITE_LIMIT) walletDataSample.limit
                .convertToStyled() else getString(R.string.not_setup)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun configureToolbar() {
        activity?.let { activity ->
            val holder = activity as ToolbarHolder
            if (!args.isEdit)
                holder.setToolbarTitle(getString(R.string.new_wallet))
            else holder.setToolbarTitle(getString(R.string.wallet_edit))
            holder.setToolbarNavigationButtonIcon(R.drawable.ic_back_arrow)
            holder.showToolbar()
        }
    }
}
