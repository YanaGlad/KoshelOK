package com.example.gladkikhvlasovtinkoff.ui.ui.transactiontype

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentSelectTransactionTypeBinding
import com.example.gladkikhvlasovtinkoff.extension.setDisabled
import com.example.gladkikhvlasovtinkoff.extension.setEnabled
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder
import dagger.hilt.android.AndroidEntryPoint
import java.lang.NullPointerException

@AndroidEntryPoint
class FragmentSelectTransactionType : ToolbarFragment() {

    private var _binding: FragmentSelectTransactionTypeBinding? = null
    private val binding get() = _binding!!

    private val args: FragmentSelectTransactionTypeArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectTransactionTypeBinding.inflate(inflater)

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
            else context?.getString(
                R.string.costs_text
            ) ?: throw NullPointerException("Context must not be null at this moment")
            val action =
                FragmentSelectTransactionTypeDirections.
                actionFragmentSelectOperationTypeToFragmentSelectOperationCategory(operationData)
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        configureToolbar()
    }

    override fun configureToolbar() {
        activity?.let{activity ->
            (activity as ToolbarHolder).setToolbarTitle(getString(R.string.choose_operation_type))
        }
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