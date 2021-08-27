package com.example.gladkikhvlasovtinkoff.ui.ui.wallets.currency

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentCurrencyChoiceBinding
import com.example.gladkikhvlasovtinkoff.extension.styleListToAppropriateForm
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.WalletDataSample
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyChoiceFragment : Fragment() {

    private val viewModel: CurrencyChoiceViewModel by viewModels()
    private lateinit var _layoutManager: CustomGridLayoutManager
    private var _binding: FragmentCurrencyChoiceBinding? = null
    private val binding get() = _binding!!
    private lateinit var currencyAdapter: CurrencyAdapter
    private var expanded = false

    private val args: CurrencyChoiceFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyChoiceBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecycler()
        viewModel.viewState.observe(viewLifecycleOwner){ viewState ->
            handleViewState(viewState)
        }
    }

    private fun handleViewState(viewState: CurrencyListViewState) =
        when(viewState){
            is CurrencyListViewState.Loaded -> submitCurrencyList(viewState.list)

            else -> onUnexpectedError()
        }

    private fun submitCurrencyList(list: List<Currency>) {
        currencyAdapter.submitList(list.styleListToAppropriateForm()) {
            currencyAdapter.setStartItemWithCode(args.walletDataSample.currency.code)
        }
    }

    private fun onUnexpectedError() {
    }

    private fun initRecycler() {
        currencyAdapter = CurrencyAdapter(object : CurrencyAdapter.OnCurrencySwitcher{
            override fun onCurrencySwitch(currency: Currency) {
               onCurrencySwitched(currency)
            }
        })
        binding.currencyRecycler.setHasFixedSize(true)
        _layoutManager = CustomGridLayoutManager(context)
        binding.currencyRecycler.apply {
            layoutManager = _layoutManager
            adapter = currencyAdapter
        }
        expandRecyclerAnimation()
    }

    private fun onCurrencySwitched(currency: Currency) {
        args.walletDataSample.currency = currency
    }

    private fun expandRecyclerAnimation() {
        binding.showMore.setOnClickListener {
            expandRecyclerView()
        }
        binding.down.setOnClickListener {
            expandRecyclerView()
        }
    }

    private fun expandRecyclerView() {
        expanded = if (expanded) {
            _layoutManager.scrollToPosition(0)
            _layoutManager.setScrollEnabled(false)
            binding.motionLayout.transitionToStart()
            binding.showMore.text = getString(R.string.show_more)
            binding.down.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_down_expand
                )
            )
            false
        } else {
            _layoutManager.setScrollEnabled(true)
            binding.motionLayout.transitionToEnd()
            binding.showMore.text = getString(R.string.hide_show_more)
            binding.down.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_up_expand
                )
            )
            true
        }
    }

}