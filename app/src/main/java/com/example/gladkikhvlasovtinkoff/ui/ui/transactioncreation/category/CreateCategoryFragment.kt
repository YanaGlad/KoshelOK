package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentConfirmTransactionCreatedBinding
import com.example.gladkikhvlasovtinkoff.databinding.FragmentCreateCategoryBinding


class CreateCategoryFragment : Fragment() {
    private var _binding: FragmentCreateCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateCategoryBinding.inflate(inflater)

        return binding.root
    }

}