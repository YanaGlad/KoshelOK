package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentCreateCategoryBinding
import com.example.gladkikhvlasovtinkoff.model.CategoryFactory
import com.example.gladkikhvlasovtinkoff.model.userCateroryKeys
import com.example.gladkikhvlasovtinkoff.ui.ui.selectcategory.OperationCategoryAdapter


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

        return binding.root
    }

    private fun initDefaultAttributes() {
        binding.name.attributeName.text = getString(R.string.wallet_name_title)
        binding.name.attributeValue.text = getString(R.string.new_category)

        binding.type.attributeName.text = getString(R.string.type_text)
        binding.type.attributeValue.text = getString(R.string.income_text)
    }

    private fun setupOperationCategoryList() {
        categoriesAdapter = OperationCategoryAdapter()

        binding.categoriesRecycler.apply {
            adapter = categoriesAdapter
            hasFixedSize()

            layoutManager = GridLayoutManager(context, 6)
        }

        context?.let { context ->
            val list = CategoryFactory().getCategories(context)

            categoriesAdapter?.addItems(CategoryFactory().getCategories(context))

        }
    }

}