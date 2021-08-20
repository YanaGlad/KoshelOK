package com.example.gladkikhvlasovtinkoff.ui.ui.transtaction

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder
import com.example.gladkikhvlasovtinkoff.databinding.FragmentWalletTransactionBinding
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionSample


class WalletTransactionFragment : ToolbarFragment() {

    private val viewModel: WalletTransactionViewModel by viewModels()
    private val args: WalletTransactionFragmentArgs by navArgs()

    private var _binding: FragmentWalletTransactionBinding? = null
    private val binding get() = _binding!!

    private lateinit var operationsAdapter: WalletOperationAdapter
    private var transaction: WalletTransactionSample? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val action = WalletTransactionFragmentDirections.actionOptionFragmentToWalletsFragment()
                    findNavController().navigate(action)
                } })

        // TODO Egor refactor
        args.newOperationData?.let { data ->
            transaction = data

            //TODO create const val for type
            if (transaction!!.type == resources.getString(R.string.costs_text)) {
                transaction!!.value = "- ${transaction!!.value}"
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
        _binding = FragmentWalletTransactionBinding.inflate(layoutInflater, container, false)

        initLayout()

        return binding.root
    }


    private fun initLayout() {
        binding.layoutWallet.info.text = getString(R.string.test_wallet_name)
        binding.layoutWallet.income.incomeText.text = getString(R.string.income_text)
        binding.layoutWallet.expenditure.expenditureText.text = getString(R.string.costs_text)
        binding.layoutWallet.buttonAddOperation.text = getString(R.string.add_operation_text)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureToolbar()
        initWalletRecycler()
        setupButtonListener()

    }

    override fun configureToolbar() {
        setHasOptionsMenu(true)
        activity?.let { activity ->
            (activity as ToolbarHolder).setToolbarTitle("")
        }
    }

    private fun initWalletRecycler() {
        operationsAdapter = WalletOperationAdapter { _, action ->
            when (action.actionId) {
                R.id.edit -> Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show()
                R.id.delete -> {
                    val deleteDialog = DeleteDialogFragment()
                    val manager = activity?.supportFragmentManager
                    manager?.let { deleteDialog.show(it, getString(R.string.delete_dialog_tag)) }
                }
            }
        }

        binding.layoutWallet.walletRecycle.setHasFixedSize(true)
        binding.layoutWallet.walletRecycle.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = operationsAdapter
        }
        operationsAdapter.submitList(viewModel.transactionList.value)


        viewModel.transactionList.observe(viewLifecycleOwner) {
            if (viewModel.transactionList.value!!.size == 0)
                binding.layoutWallet.noEntries.visibility = View.VISIBLE
            else binding.layoutWallet.noEntries.visibility = View.GONE
        }

    }

    private fun setupButtonListener() {
        val navController = findNavController()

        binding.layoutWallet.buttonAddOperation.setOnClickListener {
            val action =
                WalletTransactionFragmentDirections.actionOptionFragmentToFragmentSelectOperationValue(
                    WalletTransactionSample()
                )
            navController.navigate(action)
        }
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