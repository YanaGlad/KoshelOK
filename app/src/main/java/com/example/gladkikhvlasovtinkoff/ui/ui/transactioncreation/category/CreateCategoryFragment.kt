package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentCreateCategoryBinding
import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import com.example.gladkikhvlasovtinkoff.model.CategoryFactory
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.ui.ui.selectcategory.OperationCategoryAdapter
import com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.CategoryViewModel
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.WalletListViewState
import com.pes.androidmaterialcolorpickerdialog.ColorPicker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateCategoryFragment : Fragment(), IconHelper {
    private val viewModel: CategoryViewModel by viewModels()
    private var _binding: FragmentCreateCategoryBinding? = null
    private val binding get() = _binding!!
    private var categoriesAdapter: OperationCategoryAdapter? = null
    private val args: CreateCategoryFragmentArgs by navArgs()
    private var categoryData: CategoryDataSample? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCreateCategoryBinding.inflate(inflater)
        setupOperationCategoryList()

        initDefaultAttributes()
        enableEdit()

        categoryData = args.categoryData


        val cp = ColorPicker(activity, 89, 77, 244)

        viewModel.viewState.observe(viewLifecycleOwner) {
            handleViewState(it)
        }

        cp.setCallback {
//            val factory = CategoryFactory(activity as AppCompatActivity)
            categoryData?.colorRed = cp.red
            categoryData?.colorGreen = cp.green
            categoryData?.colorBlue = cp.blue

            // categoriesAdapter?.addItems(viewModel.getCategoryList(requireContext()))
            cp.dismiss()
        }

        binding.chooseColor.setOnClickListener {
            if (cp.isShowing) cp.dismiss() else cp.show()
        }

        binding.buttonConfirmOperationValue.setOnClickListener {
            viewModel.addCategory(categoryData!!)
            val action =
                CreateCategoryFragmentDirections.actionCreateCategoryFragmentToFragmentSelectOperationType(
                    null,
                    null,
                    false,
                    categoryData
                )
            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun handleViewState(viewState: CategoryListViewState?) {
        when (viewState) {
            is CategoryListViewState.Loaded -> {
                categoriesAdapter?.addItems(viewState.list)
            }
            else -> {
            }
        }
        binding.categoriesRecycler.apply {
            adapter = categoriesAdapter
            hasFixedSize()

            layoutManager = GridLayoutManager(context, 6)
        }
    }

    private fun enableEdit() {
        binding.nameCategory.setOnClickListener {
            val action =
                CreateCategoryFragmentDirections.actionCreateCategoryFragmentToCreateCategoryNameFragment(
                    args.categoryData
                )
            findNavController().navigate(action)
        }
        binding.typeCategory.setOnClickListener {
            val action =
                CreateCategoryFragmentDirections.actionCreateCategoryFragmentToFragmentSelectOperationType(
                    args.walletTransactionSample,
                    args.walletData?.toWalletDataSample(),
                    true,
                    args.categoryData
                )
            findNavController().navigate(action)
        }
    }

    private fun initDefaultAttributes() {
        binding.name.attributeName.text = getString(R.string.wallet_name_title)
        binding.name.attributeValue.text = args.categoryData.name

        binding.type.attributeName.text = getString(R.string.type_text)
        binding.type.attributeValue.text =
            if (args.categoryData.income) getString(R.string.income_text) else getString(R.string.costs_text)
    }

    private fun setupOperationCategoryList() {
        categoriesAdapter = OperationCategoryAdapter(this, activity as AppCompatActivity, true)

        binding.categoriesRecycler.apply {
            adapter = categoriesAdapter
            hasFixedSize()

            layoutManager = GridLayoutManager(context, 6)
        }

        context?.let { context ->
            val factory = CategoryFactory(activity as AppCompatActivity)
            val cp = ColorPicker(activity, 89, 77, 244)
            factory.colorRed = cp.red
            factory.colorBlue = cp.blue
            factory.colorGreen = cp.green

            categoriesAdapter?.addItems(factory.getCategories(context))
        }
    }

    override fun setIcon(id: String) {
        categoryData?.stringId = id
    }

}

