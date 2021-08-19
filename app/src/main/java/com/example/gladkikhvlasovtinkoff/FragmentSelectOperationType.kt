package com.example.gladkikhvlasovtinkoff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.databinding.FragmentSelectOperationTypeBinding
import java.lang.NullPointerException

class FragmentSelectOperationType : Fragment() {

    private var _binding: FragmentSelectOperationTypeBinding? = null
    private val binding get() = _binding!!

    val args: FragmentSelectOperationTypeArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
            val operationData = args.operationData
            operationData.type = if(binding.checkIncomeOperationType.visibility == View.VISIBLE)
                context?.getString(R.string.income_text) ?: throw NullPointerException("Context must not be null at this moment")
            else context?.getString(R.string.costs_text
            ) ?: throw NullPointerException("Context must not be null at this moment")
            val action =
                FragmentSelectOperationTypeDirections.
                actionFragmentSelectOperationTypeToFragmentSelectOperationCategory(operationData)
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}