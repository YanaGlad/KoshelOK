package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gladkikhvlasovtinkoff.MainActivity
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentLimitBinding
import com.example.gladkikhvlasovtinkoff.extension.setupNaviagtion
import com.example.gladkikhvlasovtinkoff.extension.setupTextStyleAndObserve
import com.example.gladkikhvlasovtinkoff.extension.styleInput


class LimitFragment : Fragment() {

    private var _binding: FragmentLimitBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNaviagtion(
            this,
            activity as AppCompatActivity,
            findNavController(),
            LimitFragmentDirections.actionLimitFragmentToNewWalletFragment()
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLimitBinding.inflate(inflater)

        binding.layoutEnter.newOperationValueField.inputType =
            InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL

        setupTextStyleAndObserve(binding.layoutEnter.newOperationValueField,  binding.layoutEnter.buttonConfirmOperationValue)

        (activity as MainActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        binding.layoutEnter.buttonConfirmOperationValue.text = getString(R.string.save)
        binding.layoutEnter.newOperationValueBlock.hint = getString(R.string.limit_description)

        binding.layoutEnter.buttonConfirmOperationValue.setOnClickListener {
            //TODO сохранение лимита и передача данных
            val action = LimitFragmentDirections.actionLimitFragmentToNewWalletFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }
}