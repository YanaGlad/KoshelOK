package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import android.os.Bundle
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

        (activity as MainActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        binding.layoutEnter.buttonConfirmOperationValue.text = getString(R.string.save)
        binding.layoutEnter.newOperationValueBlock.hint = getString(R.string.limit_description)

        return binding.root
    }

}