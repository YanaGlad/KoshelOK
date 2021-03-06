package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
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
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.CategoryListViewState
import com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.category.IconHelper
import com.example.gladkikhvlasovtinkoff.ui.ui.transtaction.DeleteDialogFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.viewmodel.CategoryViewModel
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.DeleteHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentSelectTransactionCategory : ToolbarFragment(), IconHelper ,
    DeleteHelper<CategoryDataSample> {
    private val viewModel: CategoryViewModel by viewModels()

    private var _binding: FragmentSelectTransactionCategoryBinding? = null
    private val binding get() = _binding!!
    private var categoriesAdapter: OperationCategoryAdapter? = null

    private var categoryName: String = ""
    private var imageId: Int = -1
    private var rgbColors: Triple<Int, Int, Int> = Triple(0, 0, 0)
    private var categoryId = -1L
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
        setupOperationCategoryList(args.operationData?.isIncome ?: true)
        categoriesAdapter?.checkedItem?.observe(viewLifecycleOwner) { checkedData ->
            if (checkedData != null)
                onCategoryChecked(checkedData)
            else
                onCategoryUnchecked()
        }
        binding.createCategory.setOnClickListener {
            val action =
                FragmentSelectTransactionCategoryDirections.actionFragmentSelectOperationCategoryToCreateCategoryFragment(
                    args.walletData?.createWalletDataModel(),
                    WalletTransactionSample(),
                    CategoryDataSample()
                )
            findNavController().navigate(action)
        }
        binding.buttonConfirmOperationCategory.setOnClickListener {
            onConfirm()
        }
        viewModel.viewState.observe(viewLifecycleOwner) {
            handleViewState(it)
        }
        context?.let { context ->
            viewModel.loadCategories(context = context)
        }
        viewModel.getCategoryList(args.operationData?.isIncome ?: true)
    }

    private fun handleViewState(viewState: CategoryListViewState?) {
        when (viewState) {
            is CategoryListViewState.Loaded -> {
                categoriesAdapter?.addItems(viewState.list)
            }
            is CategoryListViewState.Loading -> {

            }
            is CategoryListViewState.Error.NetworkError -> {

            }
            is CategoryListViewState.Error.UnexpectedError -> {

            }
            is CategoryListViewState.SuccessOperation -> {

            }
        }
        binding.operationCategoryList.apply {
            adapter = categoriesAdapter
            layoutManager = LinearLayoutManager(context)
        }

    }

    private fun onConfirm() {
        val operationData = if(args.operationData!=null)args.operationData else WalletTransactionSample()
        operationData?.transactionCategoryData = TransactionCategoryData(
            name = categoryName,
            iconId = imageId,
            userName = UNDEFINED_STR,
            description = UNDEFINED_STR,
            colorBlue = rgbColors.third,
            colorRed = rgbColors.first,
            colorGreen = rgbColors.second,
            income = true,
            id = categoryId
        )

        val action =
            FragmentSelectTransactionCategoryDirections.actionFragmentSelectOperationCategoryToFragmentConfirmOperationCreating(
                operationData!!, args.walletData!!
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
        categoriesAdapter = OperationCategoryAdapter(this, activity as AppCompatActivity) { category, action ->
            when (action.actionId) {
                R.id.delete -> {
                    val deleteDialog = DeleteDialogFragment(this, category)
                    val manager = activity?.supportFragmentManager
                    manager?.let {
                        deleteDialog.show(
                            it,
                            getString(R.string.delete_dialog_tag)
                        )
                    }
                }
            }
        }
        binding.operationCategoryList.apply {
            adapter = categoriesAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        categoriesAdapter = null
    }

    override fun setIcon(stringId: String, id: Long, clicked: Boolean) {
        val categoryData = args.categoryData
        categoryData?.stringId = stringId
        categoryData?.id = id
        categoryId = id
    }

    override fun delete(pos: CategoryDataSample) {
        viewModel.deleteCategory(pos)
        viewModel.loadCategories(requireContext())
        viewModel.getCategoryList(args.operationData?.isIncome ?: true)
    }
}
