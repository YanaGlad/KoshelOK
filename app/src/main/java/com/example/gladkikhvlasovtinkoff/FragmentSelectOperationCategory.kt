package com.example.gladkikhvlasovtinkoff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import com.example.gladkikhvlasovtinkoff.databinding.FragmentSelectOperationCategoryBinding
import com.example.gladkikhvlasovtinkoff.ui.selectcategory.FragmentSelectOperationCategoryArgs
import com.example.gladkikhvlasovtinkoff.ui.selectcategory.FragmentSelectOperationCategoryDirections

class FragmentSelectOperationCategory : Fragment() {

    private var _binding: FragmentSelectOperationCategoryBinding? = null
    private val binding get() = _binding!!
    private var type: String = ""
    private var sum: String = ""

    val args: FragmentSelectOperationCategoryArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.let {
            type = it.type
            sum = it.sum
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectOperationCategoryBinding.inflate(inflater)


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}