package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentConfirmTransactionCreatedBinding
import com.example.gladkikhvlasovtinkoff.databinding.FragmentCreateCategoryBinding
import com.example.gladkikhvlasovtinkoff.model.CreateCategoryFactory
import com.example.gladkikhvlasovtinkoff.model.DefaultExpensesCategoriesFactory
import com.example.gladkikhvlasovtinkoff.model.DefaultIncomeCategoriesFactory
import com.example.gladkikhvlasovtinkoff.model.TransactionCategoryDataFactory
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


        return binding.root
    }

    private fun setupOperationCategoryList() {
        categoriesAdapter = OperationCategoryAdapter()

        binding.categoriesRecycler.apply {
            adapter = categoriesAdapter
            hasFixedSize()

            layoutManager = GridLayoutManager(context, 6)
        }

        context?.let { context ->
            categoriesAdapter?.addItems(CreateCategoryFactory().getCategories(context))

        }
    }

}