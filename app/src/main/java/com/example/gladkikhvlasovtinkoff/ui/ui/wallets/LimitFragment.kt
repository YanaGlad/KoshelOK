package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gladkikhvlasovtinkoff.MainActivity
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentLimitBinding
import com.example.gladkikhvlasovtinkoff.extension.setupTextStyleAndObserve
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder


class LimitFragment : Fragment() {
    private var _binding: FragmentLimitBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLimitBinding.inflate(inflater)

        initLayout()

        binding.layoutEnter.buttonConfirmOperationValue.setOnClickListener {
            //TODO сохранение лимита и передача данных
            val action = LimitFragmentDirections.actionLimitFragmentToNewWalletFragment()
            findNavController().navigate(action)
            (activity as ToolbarHolder).setToolbarBackButtonIcon(R.drawable.ic_back_arrow)
        }

        return binding.root
    }

    private fun initLayout() {
        binding.layoutEnter.newOperationValueField.inputType =
            InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL

        binding.layoutEnter.newOperationValueField.setupTextStyleAndObserve(
            binding.layoutEnter.buttonConfirmOperationValue,
        )

        (activity as MainActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        binding.layoutEnter.buttonConfirmOperationValue.text = getString(R.string.save)
        binding.layoutEnter.newOperationValueBlock.hint = getString(R.string.limit_description)
    }
}