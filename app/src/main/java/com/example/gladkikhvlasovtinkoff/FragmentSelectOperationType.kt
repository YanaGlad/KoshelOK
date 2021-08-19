package com.example.gladkikhvlasovtinkoff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gladkikhvlasovtinkoff.databinding.FragmentConfirmOperationCreatedBinding
import com.example.gladkikhvlasovtinkoff.databinding.FragmentSelectOperationTypeBinding

class FragmentSelectOperationType : Fragment(){

    private var _binding: FragmentSelectOperationTypeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectOperationTypeBinding.inflate(inflater)

        binding.textCostsType.setOnClickListener {
            if (binding.checkCostsOperationType.visibility == View.GONE) {
                binding.checkCostsOperationType.visibility = View.VISIBLE
                binding.checkIncomeOperationType.visibility = View.GONE
            } else binding.checkCostsOperationType.visibility = View.GONE
        }

        binding.textIncomeType.setOnClickListener {
            if (binding.checkIncomeOperationType.visibility == View.GONE) {
                binding.checkIncomeOperationType.visibility = View.VISIBLE
                binding.checkCostsOperationType.visibility = View.GONE
            } else binding.checkIncomeOperationType.visibility = View.GONE
        }

        binding.buttonConfirmOperationType.setOnClickListener {
            val action =
                FragmentSelectOperationTypeDirections.actionFragmentSelectOperationTypeToFragmentSelectOperationCategory()
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}