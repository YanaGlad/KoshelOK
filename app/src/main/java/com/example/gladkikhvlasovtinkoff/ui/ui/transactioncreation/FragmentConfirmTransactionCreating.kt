package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.ErrorPresenter
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentConfirmTransactionCreatedBinding
import com.example.gladkikhvlasovtinkoff.extension.getDayString
import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionSample
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder
import com.example.gladkikhvlasovtinkoff.ui.ui.viewmodel.ConfirmCreatingViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class FragmentConfirmTransactionCreating : ToolbarFragment() {

    private var _binding: FragmentConfirmTransactionCreatedBinding? = null
    private val binding get() = _binding!!

    private val args: FragmentConfirmTransactionCreatingArgs by navArgs()
    private val viewModel: ConfirmCreatingViewModel by viewModels()
    private lateinit var transactionData: WalletTransactionSample

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.operationData.currency = args.walletData.currency
        args.operationData.walletId = args.walletData.id
        transactionData = args.operationData
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

        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            handleViewState(viewState)
        }
        setupAttributeChangeListeners()
    }

    private fun handleViewState(viewState: ConfirmCreatingViewState?) {
        when (viewState) {
            is ConfirmCreatingViewState.SuccessCreating -> onWalletCreated()
            is ConfirmCreatingViewState.Loading -> onLoading()
            is ConfirmCreatingViewState.Error.NetworkError -> onNetworkError()
            is ConfirmCreatingViewState.Error.UnexpectedError -> onUnexpectedError()
            is ConfirmCreatingViewState.Error.LimitError -> onLimitError()
        }
    }

    private fun onLimitError() {
        AlertDialog.Builder(context)
            .setMessage(getString(R.string.limit_error_message))
            .setPositiveButton(getString(R.string.OK)){ dialog, _ ->
                dialog.dismiss()
            }
    }

    private fun onUnexpectedError() {
        onLoaded()
        activity?.let { activity ->
            (activity as ErrorPresenter).showUnexpectedError()
        }
    }

    private fun onNetworkError() {
        onLoaded()
        activity?.let { activity ->
            (activity as ErrorPresenter).showNetworkError{
                onConfirm()
            }
        }
    }

    private fun onLoading() {
        binding.transactionProgressBar.visibility = View.VISIBLE
        binding.typeAttribute.attributeItemLayout.isEnabled = false
        binding.dateAttribute.attributeItemLayout.isEnabled = false
        binding.valueAttribute.attributeItemLayout.isEnabled = false
        binding.buttonConfirm.isEnabled = false
    }

    private fun onLoaded() {
        binding.transactionProgressBar.visibility = View.GONE
        binding.typeAttribute.attributeItemLayout.isEnabled = true
        binding.dateAttribute.attributeItemLayout.isEnabled = true
        binding.valueAttribute.attributeItemLayout.isEnabled = true
        binding.buttonConfirm.isEnabled = true
    }

    private fun setupAttributeChangeListeners() {
        binding.valueAttribute.attributeItemLayout.setOnClickListener {
            onNavigateToAttributes(
                FragmentConfirmTransactionCreatingDirections
                    .actionFragmentConfirmOperationCreatingToFragmentSelectOperationValue(
                        args.operationData, args.walletData
                    )
            )
        }
        binding.typeAttribute.attributeItemLayout.setOnClickListener {
            onNavigateToAttributes(
                FragmentConfirmTransactionCreatingDirections
                    .actionFragmentConfirmOperationCreatingToFragmentSelectOperationType(
                        args.operationData, args.walletData
                    )
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

    private fun onWalletCreated() {
        val action = FragmentConfirmTransactionCreatingDirections
            .actionFragmentConfirmOperationCreatingToOptionFragment(walletData = args.walletData)
        findNavController().navigate(action)
    }

    private fun showDateTimePicker() {
        activity?.let {
            val currentDate = Calendar.getInstance()
            val combinedCal: Calendar = GregorianCalendar(currentDate.timeZone)
            DatePickerDialog(
                it,
                R.style.ThemeOverlay_MaterialComponents_TimePicker,
                { view, year, monthOfYear, dayOfMonth ->
                    TimePickerDialog(
                        context,
                        R.style.ThemeOverlay_MaterialComponents_TimePicker,
                        { view, hourOfDay, minute ->
                            combinedCal.set(year, monthOfYear, dayOfMonth)
                            combinedCal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                            combinedCal.set(Calendar.MINUTE, minute)
                            applyTime(combinedCal.timeInMillis)

                        },
                        currentDate[Calendar.HOUR_OF_DAY],
                        currentDate[Calendar.MINUTE],
                        false
                    ).show()
                },
                currentDate[Calendar.YEAR],
                currentDate[Calendar.MONTH],
                currentDate[Calendar.DATE]
            ).show()
        }
    }

    private fun applyTime(timeInMillis: Long) {
        if (timeInMillis < System.currentTimeMillis()) {
            args.operationData.date = timeInMillis
            setupUiWithData(args.operationData)
        } else
            Toast.makeText(context, getString(R.string.wrong_time_message), Toast.LENGTH_LONG)
                .show()
    }

    private fun setupUiWithData(operationData: WalletTransactionSample) {
        if (args.isEdit) {
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
