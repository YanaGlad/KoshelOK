package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentCreateCategoryBinding
import com.example.gladkikhvlasovtinkoff.model.CategoryFactory
import com.example.gladkikhvlasovtinkoff.model.WalletDataSample
import com.example.gladkikhvlasovtinkoff.ui.ui.selectcategory.OperationCategoryAdapter
import com.example.gladkikhvlasovtinkoff.ui.ui.transtaction.WalletTransactionFragmentArgs
import com.pes.androidmaterialcolorpickerdialog.ColorPicker


class CreateCategoryFragment : Fragment() {
    private var _binding: FragmentCreateCategoryBinding? = null
    private val binding get() = _binding!!
    private var categoriesAdapter: OperationCategoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateCategoryBinding.inflate(inflater)
        setupOperationCategoryList()

        initDefaultAttributes()

        binding.nameCategory.setOnClickListener {
            val action =
                CreateCategoryFragmentDirections.actionCreateCategoryFragmentToCreateCategoryNameFragment()
            findNavController().navigate(action)
        }
        binding.typeCategory.setOnClickListener {
        }

        val cp = ColorPicker(activity, 89, 77, 244)

        cp.setCallback {
            val factory = CategoryFactory(activity as AppCompatActivity)
            factory.colorRed = cp.red
            factory.colorGreen = cp.green
            factory.colorBlue = cp.blue

            categoriesAdapter?.addItems(factory.getCategories(requireContext()))
            cp.dismiss()
        }

        binding.chooseColor.setOnClickListener {
            if (cp.isShowing) cp.dismiss() else cp.show()
        }

        return binding.root
    }

    private fun initDefaultAttributes() {
        binding.name.attributeName.text = getString(R.string.wallet_name_title)
        binding.name.attributeValue.text = getString(R.string.new_category)

        binding.type.attributeName.text = getString(R.string.type_text)
        binding.type.attributeValue.text = getString(R.string.income_text)
    }

    private fun setupOperationCategoryList() {
        categoriesAdapter = OperationCategoryAdapter(activity as AppCompatActivity, true)

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

}