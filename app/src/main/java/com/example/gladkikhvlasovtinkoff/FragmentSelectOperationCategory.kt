package com.example.gladkikhvlasovtinkoff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gladkikhvlasovtinkoff.databinding.FragmentConfirmOperationCreatedBinding
import com.example.gladkikhvlasovtinkoff.databinding.FragmentSelectOperationCategoryBinding

class FragmentSelectOperationCategory : Fragment(){

    private var _binding: FragmentSelectOperationCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectOperationCategoryBinding.inflate(inflater)
        binding.buttonConfirmOperationCategory.setOnClickListener {
            val action = FragmentSelectOperationCategoryDirections.actionFragmentSelectOperationCategoryToFragmentConfirmOperationCreating()
            findNavController().navigate(action)
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}