package com.example.gladkikhvlasovtinkoff

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gladkikhvlasovtinkoff.databinding.FragmentConfirmOperationCreatedBinding

class FragmentConfirmOperationCreating : Fragment(){

    private var _binding: FragmentConfirmOperationCreatedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmOperationCreatedBinding.inflate(inflater)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
