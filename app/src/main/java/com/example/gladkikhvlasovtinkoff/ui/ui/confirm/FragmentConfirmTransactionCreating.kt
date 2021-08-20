package com.example.gladkikhvlasovtinkoff.ui.ui.confirm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentConfirmTransactionCreatedBinding
import com.example.gladkikhvlasovtinkoff.extension.getDayString
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionSample
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentConfirmTransactionCreating : ToolbarFragment(){

    private var _binding: FragmentConfirmTransactionCreatedBinding? = null
    private val binding get() = _binding!!

    val args: FragmentConfirmTransactionCreatingArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmTransactionCreatedBinding.inflate(inflater)

        setupUiWithData(args.operationData)

        binding.buttonConfirmOperationCreating.setOnClickListener{
            val operationData = args.operationData
            operationData.date = System.currentTimeMillis()
            val action =
                FragmentConfirmTransactionCreatingDirections.
                actionFragmentConfirmOperationCreatingToOptionFragment(
                    operationData
                )
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
            (activity as ToolbarHolder).setToolbarTitle(getString(R.string.new_operation))
        }
    }

    private fun setupUiWithData(operationData: WalletTransactionSample) {
        binding.valueAttribute.attributeName.text = getString(R.string.value_text)
        binding.valueAttribute.attributeValue.text = operationData.amount

        binding.typeAttribute.attributeName.text = getString(R.string.type_text)
        binding.typeAttribute.attributeValue.text = if(operationData.isIncome)
            getString(R.string.income_text) else getString(R.string.costs_text)

        binding.categoryAttribute.attributeName.text = getString(R.string.category_text)
        binding.categoryAttribute.attributeValue.text = operationData.transactionCategoryData.name

        binding.dateAttribute.attributeName.text = getString(R.string.operation_date_text)
        context?.let { context ->
            binding.dateAttribute.attributeValue.text = System.currentTimeMillis().getDayString(context)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
