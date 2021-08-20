package com.example.gladkikhvlasovtinkoff.ui.ui.selectcategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSelectTransactionCategory : ToolbarFragment() {

    private var _binding: FragmentSelectTransactionCategoryBinding? = null
    private val binding get() = _binding!!
    private var categoriesAdapter: OperationCategoryAdapter? = null

    private var categoryName: String = ""
    private var imageId: Int = -1


    private val args: FragmentSelectTransactionCategoryArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectTransactionCategoryBinding.inflate(inflater)

        binding.buttonConfirmOperationCategory.setOnClickListener {
            val operationData = args.operationData
            //TODO - исправить id = 0 и description = ""
            operationData.transactionCategoryData = TransactionCategoryData(
                name = categoryName,
                iconId = imageId,
                id = 0,
                description = ""
            )
            val action =
                FragmentSelectTransactionCategoryDirections.
                actionFragmentSelectOperationCategoryToFragmentConfirmOperationCreating(
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
            (activity as ToolbarHolder).setToolbarTitle(getString(R.string.choose_category))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupOperationCategoryList(args.operationData.isIncome)
        categoriesAdapter?.checkedItem?.observe(viewLifecycleOwner) { checkedData ->
            if (checkedData != null)
                onCategoryChecked(checkedData)
            else
                onCategoryUnchecked()
        }

    }


    private fun onCategoryUnchecked() {
        binding.buttonConfirmOperationCategory.setDisabled(context)

    }

    private fun onCategoryChecked(checkedData: TransactionCategoryData) {
        binding.buttonConfirmOperationCategory.setEnabled(context)
        categoryName = checkedData.name
        imageId = checkedData.iconId
    }


    private fun setupOperationCategoryList(isIncome : Boolean) {
        val categoryDataFactory: TransactionCategoryDataFactory =
            if(isIncome) DefaultIncomeCategoriesFactory()
            else DefaultExpensesCategoriesFactory()
        categoriesAdapter = OperationCategoryAdapter()
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

}
