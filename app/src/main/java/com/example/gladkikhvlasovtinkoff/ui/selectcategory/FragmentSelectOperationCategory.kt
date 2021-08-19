package com.example.gladkikhvlasovtinkoff.ui.selectcategory

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentSelectOperationCategoryBinding
import com.example.gladkikhvlasovtinkoff.model.OperationCategoryData
import com.example.gladkikhvlasovtinkoff.model.OperationCategoryDataFactory
import com.example.gladkikhvlasovtinkoff.model.OperationCategoryDataFactoryImpl

class FragmentSelectOperationCategory : Fragment(){

    private var _binding: FragmentSelectOperationCategoryBinding? = null
    private val binding get() = _binding!!
    private var categoriesAdapter : OperationCategoryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectOperationCategoryBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupOperationCategoryList()
        categoriesAdapter?.checkedItem?.observe(viewLifecycleOwner){ checkedData ->
            if(checkedData != null)
                onCategoryChecked(checkedData)
            else
                onCategoryUnchecked()
        }
    }

    private fun onCategoryUnchecked() {
        makeNextButtonDisabled()

    }

    private fun makeNextButtonDisabled() {
        binding.buttonConfirmOperationCategory.background =
            ResourcesCompat.getDrawable(resources,
                R.drawable.disabled_button_back_with_corner_radius, context?.theme)
        binding.buttonConfirmOperationCategory.isEnabled = false
        binding.buttonConfirmOperationCategory.setTextColor(Color.BLACK)

    }

    private fun onCategoryChecked(checkedData: OperationCategoryData) {
        makeNextButtonActive()
    }

    private fun makeNextButtonActive() {
        binding.buttonConfirmOperationCategory.background =
            ResourcesCompat.getDrawable(resources,
                R.drawable.active_button_back_with_corner_radius, context?.theme)
        binding.buttonConfirmOperationCategory.isEnabled = true
        binding.buttonConfirmOperationCategory.setTextColor(Color.WHITE)
    }

    private fun setupOperationCategoryList() {
        val categoryDataFactory : OperationCategoryDataFactory =
            OperationCategoryDataFactoryImpl()
        categoriesAdapter = OperationCategoryAdapter()
        binding.operationCategoryList.apply {
            adapter = categoriesAdapter
            layoutManager = LinearLayoutManager(context)
        }
        categoriesAdapter?.addItems(categoryDataFactory.getCategories())
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        categoriesAdapter = null
    }

}
