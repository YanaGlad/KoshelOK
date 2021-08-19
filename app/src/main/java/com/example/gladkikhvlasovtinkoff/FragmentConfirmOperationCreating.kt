package com.example.gladkikhvlasovtinkoff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.databinding.FragmentConfirmOperationCreatedBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class FragmentConfirmOperationCreating : Fragment(){

    private var _binding: FragmentConfirmOperationCreatedBinding? = null
    private val binding get() = _binding!!

    val args: FragmentConfirmOperationCreatingArgs by navArgs()

    private var type = ""
    private var category = -1
    private var sum = ""
    private var imageId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.let {
            sum = it.sum
            category = it.category
            type = it.type
            imageId = it.imageId
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmOperationCreatedBinding.inflate(inflater)

        binding.toolBar.text.text = resources.getString(R.string.new_operation)

        binding.valueAttribute.attributeName.text = "Сумма"
        binding.valueAttribute.attributeValue.text = sum

        binding.typeAttribute.attributeName.text = "Тип"
        binding.typeAttribute.attributeValue.text = type

        binding.categoryAttribute.attributeName.text = "Категория"
        binding.categoryAttribute.attributeValue.text = resources.getString(category)

        val date = Date()
        val strDateFormat = "yyyy.MM.dd"
        val dateFormat: DateFormat = SimpleDateFormat(strDateFormat)
        val formattedDate: String = dateFormat.format(date)

        binding.dateAttribute.attributeName.text = "Дата операции"
        binding.dateAttribute.attributeValue.text = formattedDate



        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
