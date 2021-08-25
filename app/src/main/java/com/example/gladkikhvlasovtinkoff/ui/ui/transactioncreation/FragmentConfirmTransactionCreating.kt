package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentConfirmTransactionCreatedBinding
import com.example.gladkikhvlasovtinkoff.extension.getDayString
import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
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
    private val viewModel : ConfirmCreatingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.operationData.currency = args.walletData.currency
        args.operationData.walletId = args.walletData.id
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
                showDateTimePicker()
            }
        }

        viewModel.viewState.observe(viewLifecycleOwner){ viewState ->
            handleViewState(viewState)
        }
        setupAttributeChangeListeners()
    }

    private fun handleViewState(viewState: ConfirmCreatingViewState?) {
        when(viewState){
            is ConfirmCreatingViewState.SuccessCreating -> onWalletCreated()
            else -> {}
        }
    }

    private fun setupAttributeChangeListeners() {
        binding.valueAttribute.attributeItemLayout.setOnClickListener {
            onNavigateToAttributes(
                FragmentConfirmTransactionCreatingDirections
                    .actionFragmentConfirmOperationCreatingToFragmentSelectOperationValue(
                        args.operationData, args.walletData )
            )
        }
        binding.typeAttribute.attributeItemLayout.setOnClickListener {
            onNavigateToAttributes(
                FragmentConfirmTransactionCreatingDirections
                    .actionFragmentConfirmOperationCreatingToFragmentSelectOperationType(
                        args.operationData, args.walletData )
            )
        }
        binding.categoryAttribute.attributeItemLayout.setOnClickListener {
            onNavigateToAttributes(
                FragmentConfirmTransactionCreatingDirections
                    .actionFragmentConfirmOperationCreatingToFragmentSelectOperationCategory(
                        args.operationData, args.walletData, CategoryDataSample()
                    )
            )
        }
    }

    private fun onNavigateToAttributes(action: NavDirections) {
        findNavController().navigate(action)
    }

    private fun onConfirm() {
        context?.let {
            viewModel.addTransaction(it, args.operationData)
        }
    }

    private fun onWalletCreated(){
        val action = FragmentConfirmTransactionCreatingDirections
            .actionFragmentConfirmOperationCreatingToOptionFragment(walletData = args.walletData)
        findNavController().navigate(action)
    }

    private fun showDateTimePicker() {
        activity?.let{
            val currentDate = Calendar.getInstance()
            val combinedCal: Calendar = GregorianCalendar(currentDate.timeZone)
            DatePickerDialog(it, R.style.ThemeOverlay_MaterialComponents_TimePicker, { view, year, monthOfYear, dayOfMonth ->
                TimePickerDialog(
                    context,
                    R.style.ThemeOverlay_MaterialComponents_TimePicker,
                    { view, hourOfDay, minute ->
                        combinedCal.set(year, monthOfYear, dayOfMonth)
                        combinedCal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        combinedCal.set(Calendar.MINUTE, minute)
                        val timeMillis = combinedCal.timeInMillis
                        args.operationData.date = timeMillis
                        setupUiWithData(args.operationData)
                    },
                    currentDate[Calendar.HOUR_OF_DAY],
                    currentDate[Calendar.MINUTE],
                    false
                ).show()
            },
                currentDate[Calendar.YEAR],
                currentDate[Calendar.MONTH],
                currentDate[Calendar.DATE]).show()
        }
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
        if(args.isEdit){
            binding.buttonConfirm.setText(R.string.remove)
        }

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
