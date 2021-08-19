package com.example.gladkikhvlasovtinkoff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.databinding.FragmentSelectOperationTypeBinding
import com.example.gladkikhvlasovtinkoff.extension.setDisabled
import com.example.gladkikhvlasovtinkoff.extension.setEnabled
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
                enableButton()
            } else{
                binding.checkCostsOperationType.visibility = View.GONE
                disableButton()
            }
        }

        binding.textIncomeType.setOnClickListener {
            if (binding.checkIncomeOperationType.visibility == View.GONE) {
                binding.checkIncomeOperationType.visibility = View.VISIBLE
                binding.checkCostsOperationType.visibility = View.GONE
                enableButton()
            } else {
                binding.checkIncomeOperationType.visibility = View.GONE
                disableButton()
            }
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

    override fun onResume() {
        super.onResume()

        (activity as MainActivity?)
            ?.setActionBarTitle(getString(R.string.choose_operation_type))
    }

    private fun disableButton(){
        binding.buttonConfirmOperationType.setDisabled(context)
    }

    private fun enableButton(){
        binding.buttonConfirmOperationType.setEnabled(context)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}