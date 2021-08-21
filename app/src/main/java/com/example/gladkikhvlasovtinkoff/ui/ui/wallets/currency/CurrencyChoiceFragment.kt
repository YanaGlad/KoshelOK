package com.example.gladkikhvlasovtinkoff.ui.ui.wallets.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentCurrencyChoiceBinding


class CurrencyChoiceFragment : Fragment() {
    private val viewModel: CurrencyChoiceViewModel by viewModels()
    private lateinit var _layoutManager: CustomGridLayoutManager
    private var _binding: FragmentCurrencyChoiceBinding? = null
    private val binding get() = _binding!!
    private lateinit var currencyAdapter: CurrencyAdapter
    private var expanded = false

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
}