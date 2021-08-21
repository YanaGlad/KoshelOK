package com.example.gladkikhvlasovtinkoff.ui.ui.wallets.currency

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gladkikhvlasovtinkoff.databinding.FragmentCurrencyChoiceBinding


class CurrencyChoiceFragment : Fragment() {
    private val viewModel: CurrencyChoiceViewModel by viewModels()

    private var _binding: FragmentCurrencyChoiceBinding? = null
    private val binding get() = _binding!!
    private lateinit var currencyAdapter: CurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyChoiceBinding.inflate(inflater)

        initRecyler()


        return binding.root
    }

    private fun initRecyler() {
        currencyAdapter = CurrencyAdapter()

        binding.currencyRecycler.setHasFixedSize(true)
        binding.currencyRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = currencyAdapter
        }
        currencyAdapter.submitList(viewModel.currencyList.value)
    }

}