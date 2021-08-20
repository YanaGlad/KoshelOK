package com.example.gladkikhvlasovtinkoff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gladkikhvlasovtinkoff.databinding.FragmentConfirmTransactionCreatedBinding
import com.example.gladkikhvlasovtinkoff.extension.getDayString
import com.example.gladkikhvlasovtinkoff.model.WalletOperationBuilder

class FragmentConfirmTransactionCreating : ToolbarFragment(){

    private var _binding: FragmentConfirmTransactionCreatedBinding? = null
    private val binding get() = _binding!!

    val args: FragmentConfirmTransactionCreatingArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmTransactionCreatedBinding.inflate(inflater)

        setupUiWithData(args.operationData)

        binding.buttonConfirmOperationCreating.setOnClickListener{
            val action =
                FragmentConfirmTransactionCreatingDirections.
                actionFragmentConfirmOperationCreatingToOptionFragment(
                    args.operationData
                )
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        configureToolbar()
    }

    override fun configureToolbar() {
        activity?.let{activity ->
            (activity as ToolbarHolder).setToolbarTitle(getString(R.string.new_operation))
        }
    }

    private fun setupUiWithData(operationData: WalletOperationBuilder) {
        binding.valueAttribute.attributeName.text = getString(R.string.value_text)
        binding.valueAttribute.attributeValue.text = operationData.value

        binding.typeAttribute.attributeName.text = getString(R.string.type_text)
        binding.typeAttribute.attributeValue.text = operationData.type

        binding.categoryAttribute.attributeName.text = getString(R.string.category_text)
        binding.categoryAttribute.attributeValue.text = resources.getString(operationData.categoryTextId)

        binding.dateAttribute.attributeName.text = getString(R.string.operation_date_text)
        context?.let { context ->
            binding.dateAttribute.attributeValue.text = System.currentTimeMillis().getDayString(context)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
