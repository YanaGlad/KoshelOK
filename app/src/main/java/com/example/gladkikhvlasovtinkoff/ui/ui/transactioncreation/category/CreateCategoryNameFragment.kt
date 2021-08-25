package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentCreateCategoryBinding
import com.example.gladkikhvlasovtinkoff.databinding.FragmentCreateCategoryNameBinding
import com.example.gladkikhvlasovtinkoff.extension.observeTextChanged
import com.example.gladkikhvlasovtinkoff.extension.setDisabled
import com.example.gladkikhvlasovtinkoff.extension.setEnabled
import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import com.google.android.material.textfield.TextInputEditText


class CreateCategoryNameFragment : Fragment() {

    private var _binding: FragmentCreateCategoryNameBinding? = null
    private val binding get() = _binding!!

    private val args: CreateCategoryFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateCategoryNameBinding.inflate(layoutInflater)

        binding.layoutEnter.newOperationValueField.observeTextChanged(binding.layoutEnter.buttonConfirmOperationValue)
        binding.layoutEnter.newOperationValueField.setText(args.categoryData.name)

        binding.layoutEnter.buttonConfirmOperationValue.setOnClickListener {
            val data : CategoryDataSample = args.categoryData
            data.name = binding.layoutEnter.newOperationValueField.text.toString()
            val action =
                CreateCategoryNameFragmentDirections.actionCreateCategoryNameFragmentToCreateCategoryFragment(
                    args.walletData,
                    args.walletTransactionSample,
                    data
                )
            findNavController().navigate(action)
        }

        return binding.root
    }


}