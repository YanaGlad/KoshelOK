package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentConfirmTransactionCreatedBinding
import com.example.gladkikhvlasovtinkoff.extension.getDayString
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionSample
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class FragmentConfirmTransactionCreating : ToolbarFragment() {

    private var _binding: FragmentConfirmTransactionCreatedBinding? = null
    private val binding get() = _binding!!

    private val args: FragmentConfirmTransactionCreatingArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO - СДЕЛАТЬ ВЫБОР ВАЛЮТЫ
        args.operationData.currency = Currency(
            id = 555,
            code = "rub",
            name = "Rubli blin"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmTransactionCreatedBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureToolbar()
        setupUiWithData(args.operationData)

        binding.buttonConfirm.setOnClickListener {
            onConfirm()
        }
        binding.dateAttribute.attributeItemLayout.setOnClickListener {
            activity?.let { activity ->
                showDatePicker(activity)
            }
        }
        setupAttributeChangeListeners()
    }

    private fun setupAttributeChangeListeners() {
        binding.valueAttribute.attributeItemLayout.setOnClickListener {
            onNavigateToAttributes(
                FragmentConfirmTransactionCreatingDirections
                    .actionFragmentConfirmOperationCreatingToFragmentSelectOperationValue(args.operationData)
            )
        }
        binding.typeAttribute.attributeItemLayout.setOnClickListener {
            onNavigateToAttributes(
                FragmentConfirmTransactionCreatingDirections
                    .actionFragmentConfirmOperationCreatingToFragmentSelectOperationType(args.operationData)
            )
        }
        binding.categoryAttribute.attributeItemLayout.setOnClickListener {
            onNavigateToAttributes(
                FragmentConfirmTransactionCreatingDirections
                    .actionFragmentConfirmOperationCreatingToFragmentSelectOperationCategory(args.operationData)
            )
        }
    }

    private fun onNavigateToAttributes(action: NavDirections) {
        findNavController().navigate(action)
    }

    private fun onConfirm() {
        val operationData = args.operationData
        if (!operationData.isDateDefined)
            operationData.date = System.currentTimeMillis()
        val action =
            FragmentConfirmTransactionCreatingDirections.actionFragmentConfirmOperationCreatingToOptionFragment(
                operationData, operationData.walletId
            )
        findNavController().navigate(action)
    }

    private fun showDatePicker(activity: Activity) {
        val cal = Calendar.getInstance()
        DatePickerDialog(
            activity, getDatePickerListener(cal),
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun getDatePickerListener(cal: Calendar): DatePickerDialog.OnDateSetListener =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            args.operationData.date = cal.timeInMillis
            setupUiWithData(args.operationData)
        }

    private fun setupUiWithData(operationData: WalletTransactionSample) {
        binding.valueAttribute.attributeName.text = getString(R.string.value_text)
        binding.valueAttribute.attributeValue.text = operationData.amount
        binding.typeAttribute.attributeName.text = getString(R.string.type_text)
        binding.typeAttribute.attributeValue.text = if (operationData.isIncome)
            getString(R.string.income_text) else getString(R.string.costs_text)
        binding.categoryAttribute.attributeName.text = getString(R.string.category_text)
        binding.categoryAttribute.attributeValue.text = operationData.transactionCategoryData.name
        binding.dateAttribute.attributeName.text = getString(R.string.operation_date_text)
        context?.let { context ->
            if (!operationData.isDateDefined)
                operationData.date = System.currentTimeMillis()
            binding.dateAttribute.attributeValue.text = operationData.date.getDayString(context)
        }
    }

    override fun onResume() {
        super.onResume()
        configureToolbar()
    }

    override fun configureToolbar() {
        activity?.let { activity ->
            (activity as ToolbarHolder).setToolbarTitle(getString(R.string.new_operation))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
