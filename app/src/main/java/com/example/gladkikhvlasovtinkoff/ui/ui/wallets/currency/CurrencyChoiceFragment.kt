package com.example.gladkikhvlasovtinkoff.ui.ui.wallets.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.MainActivity
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentCurrencyChoiceBinding
import com.example.gladkikhvlasovtinkoff.extension.setupNavigation
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.WalletDataSample


interface OnCurrencySwitcher {
    fun changeViewModel(currency: Currency)
}

class CurrencyChoiceFragment : Fragment(), OnCurrencySwitcher {
    private val viewModel: CurrencyChoiceViewModel by viewModels()
    private lateinit var _layoutManager: CustomGridLayoutManager
    private var _binding: FragmentCurrencyChoiceBinding? = null
    private val binding get() = _binding!!
    private lateinit var currencyAdapter: CurrencyAdapter
    private var expanded = false

    private val args: CurrencyChoiceFragmentArgs by navArgs()
    private lateinit var walletDataSample: WalletDataSample

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyChoiceBinding.inflate(inflater)

        viewModel.dataSample.value = args.walletDataSample
        walletDataSample = args.walletDataSample

        viewModel.dataSample.observe(viewLifecycleOwner) {
            walletDataSample = viewModel.dataSample.value!!
        }

        initRecyler()

        return binding.root
    }

    private fun initRecyler() {
        currencyAdapter = CurrencyAdapter(this)
        binding.currencyRecycler.setHasFixedSize(true)
        _layoutManager = CustomGridLayoutManager(context)
        binding.currencyRecycler.apply {
            layoutManager = _layoutManager
            adapter = currencyAdapter
        }
        currencyAdapter.submitList(viewModel.currencyList.value)

        expandRecyclerAnimation()
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

    override fun changeViewModel(currency: Currency) {
        val change = viewModel.dataSample
        change.value!!.currency = currency

        viewModel.dataSample.value = change.value
    }
}