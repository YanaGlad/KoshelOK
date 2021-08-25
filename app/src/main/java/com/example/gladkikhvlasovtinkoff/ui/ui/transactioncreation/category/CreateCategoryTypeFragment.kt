package com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentCreateCategoryNameBinding
import com.example.gladkikhvlasovtinkoff.databinding.FragmentCreateCategoryTypeBinding


class CreateCategoryTypeFragment : Fragment() {
    private var _binding: FragmentCreateCategoryTypeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateCategoryTypeBinding.inflate(layoutInflater)

        return binding.root
    }

}