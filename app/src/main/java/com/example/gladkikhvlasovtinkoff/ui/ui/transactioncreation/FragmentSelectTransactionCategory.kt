package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder
import com.example.gladkikhvlasovtinkoff.databinding.FragmentSelectTransactionCategoryBinding
import com.example.gladkikhvlasovtinkoff.extension.setDisabled
import com.example.gladkikhvlasovtinkoff.extension.setEnabled
import com.example.gladkikhvlasovtinkoff.model.*
import com.example.gladkikhvlasovtinkoff.ui.ui.selectcategory.OperationCategoryAdapter
import com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.category.IconHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSelectTransactionCategory : ToolbarFragment(), IconHelper {

    private var _binding: FragmentSelectTransactionCategoryBinding? = null
    private val binding get() = _binding!!
    private var categoriesAdapter: OperationCategoryAdapter? = null

    private var categoryName: String = ""
    private var imageId: Int = -1
    private var rgbColors: Triple<Int, Int, Int> = Triple(0, 0, 0)

    private val args: FragmentSelectTransactionCategoryArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectTransactionCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureToolbar()
        setupOperationCategoryList(args.operationData!!.isIncome)
        categoriesAdapter?.checkedItem?.observe(viewLifecycleOwner) { checkedData ->
            if (checkedData != null)
                onCategoryChecked(checkedData)
            else
                onCategoryUnchecked()
        }

        binding.createCategory.setOnClickListener {
            val action =
                FragmentSelectTransactionCategoryDirections.actionFragmentSelectOperationCategoryToCreateCategoryFragment(
                    null,
                    null,
                    CategoryDataSample()
                )
            findNavController().navigate(action)
        }

        binding.buttonConfirmOperationCategory.setOnClickListener {
            onConfirm()
        }
    }

    private fun onConfirm() {
        val operationData = args.operationData
        operationData?.transactionCategoryData = TransactionCategoryData(
            name = categoryName,
            iconId = imageId,
            id = UNDEFINED_ID.toLong(),
            description = UNDEFINED_STR,
            colorBlue = rgbColors.third,
            colorRed = rgbColors.first,
            colorGreen = rgbColors.second
        )
        val action =
            FragmentSelectTransactionCategoryDirections.actionFragmentSelectOperationCategoryToFragmentConfirmOperationCreating(
                args.operationData!!, args.walletData!!
            )
        findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        configureToolbar()
    }

    override fun configureToolbar() {
        activity?.let { activity ->
            (activity as ToolbarHolder).setToolbarTitle(getString(R.string.choose_category))
        }
    }

    private fun onCategoryUnchecked() {
        binding.buttonConfirmOperationCategory.setDisabled(context)
    }

    private fun onCategoryChecked(checkedData: TransactionCategoryData) {
        binding.buttonConfirmOperationCategory.setEnabled(context)
        categoryName = checkedData.name
        imageId = checkedData.iconId
        rgbColors = Triple(checkedData.colorRed, checkedData.colorGreen, checkedData.colorBlue)
    }

    private fun setupOperationCategoryList(isIncome: Boolean) {
        val categoryDataFactory: TransactionCategoryDataFactory =
            if (isIncome) DefaultIncomeCategoriesFactory()
            else DefaultExpensesCategoriesFactory()
        categoriesAdapter = OperationCategoryAdapter( this, activity as AppCompatActivity)
        binding.operationCategoryList.apply {
            adapter = categoriesAdapter
            layoutManager = LinearLayoutManager(context)
        }
        context?.let { context ->
            categoriesAdapter?.addItems(categoryDataFactory.getCategories(context))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        categoriesAdapter = null
    }

    override fun setIcon(id: String) {
        TODO("Not yet implemented")
    }
}
