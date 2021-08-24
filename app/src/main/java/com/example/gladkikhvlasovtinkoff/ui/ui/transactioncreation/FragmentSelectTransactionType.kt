package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureToolbar()
        setupSelectTypeListeners()
        binding.buttonConfirmOperationType.setOnClickListener {
            onNextNavigate()
        }
    }

    private fun setupSelectTypeListeners() {
        binding.expensesLayout.setOnClickListener {
            if (binding.checkCostsOperationType.visibility == View.INVISIBLE) {
                binding.checkCostsOperationType.visibility = View.VISIBLE
                binding.checkIncomeOperationType.visibility = View.INVISIBLE
                enableButton()
            } else{
                binding.checkCostsOperationType.visibility = View.INVISIBLE
                disableButton()
            }
        }
        binding.incomeLayout.setOnClickListener {
            if (binding.checkIncomeOperationType.visibility == View.INVISIBLE) {
                binding.checkIncomeOperationType.visibility = View.VISIBLE
                binding.checkCostsOperationType.visibility = View.INVISIBLE
                enableButton()
            } else {
                binding.checkIncomeOperationType.visibility = View.INVISIBLE
                disableButton()
            }
        }
    }

    private fun onNextNavigate(){
        val operationData = args.operationData
        operationData.isIncome = binding.checkIncomeOperationType.visibility == View.VISIBLE
        val action =
            FragmentSelectTransactionTypeDirections.
            actionFragmentSelectOperationTypeToFragmentSelectOperationCategory(operationData,
                args.walletData)
        findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        configureToolbar()
    }

    private fun disableButton(){
        binding.buttonConfirmOperationType.setDisabled(context)
    }

    private fun enableButton(){
        binding.buttonConfirmOperationType.setEnabled(context)

    }

    override fun configureToolbar() {
        activity?.let{activity ->
            (activity as ToolbarHolder).setToolbarTitle(getString(R.string.choose_operation_type))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
