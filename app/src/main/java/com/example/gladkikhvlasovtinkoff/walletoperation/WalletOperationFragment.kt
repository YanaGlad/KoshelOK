package com.example.gladkikhvlasovtinkoff.walletoperation

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gladkikhvlasovtinkoff.EasyTimer
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentWalletOperationBinding
import com.example.gladkikhvlasovtinkoff.model.WalletOperationBuilder
import com.example.gladkikhvlasovtinkoff.walletoperation.dialog.DeleteDialogFragment


class WalletOperationFragment : Fragment() {

    private val viewModel: WalletOperationViewModel by viewModels()
    val args: WalletOperationFragmentArgs by navArgs()

    private var _binding: FragmentWalletOperationBinding? = null
    private val binding get() = _binding!!
    private var timer = EasyTimer()

    private lateinit var adapter: WalletOperationAdapter
    private var transaction: WalletOperationBuilder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        timer.stopTimer()

        args.let {
            if (it.newOperationData != null) {
                transaction = it.newOperationData!!

                if (transaction!!.type == resources.getString(R.string.costs_text)) {
                    transaction!!.value = "- ${transaction!!.value}"
                }
            }
        }
        //Добавление данных в удаленную бд
        //Обновление списка в соотвествии с данными бд
        if (transaction != null)
            viewModel.transactionList.value?.add(transaction!!.createModel())
    }


override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View {
    _binding = FragmentWalletOperationBinding.inflate(layoutInflater, container, false)
    return binding.root
}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    setHasOptionsMenu(true);

    initWalletRecycler(binding.walletRecycle)

    adapter = WalletOperationAdapter() { item, action ->
        when (action.actionId) {
            R.id.edit -> Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show()
            R.id.delete -> {
                val deleteDialog = DeleteDialogFragment()
                val manager = activity?.supportFragmentManager
                deleteDialog.show(manager!!, "myDialog")
             }
        }
    }
    binding.walletRecycle.adapter = adapter

    adapter.submitList(viewModel.transactionList.value)
    val navController = findNavController()

    binding.buttonAddOperation.setOnClickListener {
        val action =
            WalletOperationFragmentDirections.actionOptionFragmentToFragmentSelectOperationValue(
                WalletOperationBuilder()
            )
        navController.navigate(action)
    }


}

private fun initWalletRecycler(view: RecyclerView) {
    view.setHasFixedSize(true)
    view.layoutManager = LinearLayoutManager(context)
}

override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.menu_main, menu);
    super.onCreateOptionsMenu(menu, inflater);
}

override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.action_settings) {
        Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
    }
    return true
}


override fun onDestroy() {
    super.onDestroy()
    _binding = null
}
}