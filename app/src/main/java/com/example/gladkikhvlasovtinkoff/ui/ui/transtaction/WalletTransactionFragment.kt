package com.example.gladkikhvlasovtinkoff.ui.ui.transtaction

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder
import com.example.gladkikhvlasovtinkoff.databinding.FragmentWalletTransactionBinding
import com.example.gladkikhvlasovtinkoff.extension.setupNavigation
import com.example.gladkikhvlasovtinkoff.extension.toDelegateItemListWithDate
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionSample
import com.example.gladkikhvlasovtinkoff.ui.ui.delegates.*
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.WalletListViewState


class WalletTransactionFragment : ToolbarFragment() {

    private val viewModel: WalletTransactionViewModel by viewModels()

    private var _binding: FragmentWalletTransactionBinding? = null
    private val binding get() = _binding!!

    private var baseAdapter: BaseAdapter? = null

    private val args: WalletTransactionFragmentArgs by navArgs()
    private var transaction: WalletTransactionSample? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigation(
            fragment = this,
            activity = activity as AppCompatActivity,
            navController = findNavController(),
            action = WalletTransactionFragmentDirections.actionOptionFragmentToWalletsFragment()
        )
        //TODO - доделать обработку приходящего объекта ЕГОР
        args.newOperationData?.let { data ->
            transaction = data
        }

        if (transaction != null)
            viewModel.transactionList.add(transaction!!.createModel())
    }

//    private fun handleViewState(viewState: TransactionListViewState?) {
//        when (viewState) {
//            is TransactionListViewState.Loaded -> baseAdapter?.
//            submitList(viewState.list)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalletTransactionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun initLayout() {
        binding.layoutWallet.hiddenWalletRecycle.visibility = View.GONE
        binding.layoutWallet.down.visibility = View.GONE
        binding.layoutWallet.showMore.visibility = View.GONE
        binding.layoutWallet.info.text = getString(R.string.test_wallet_name)
        binding.layoutWallet.income.incomeText.text = getString(R.string.income_text)
        binding.layoutWallet.expenditure.expenditureText.text = getString(R.string.costs_text)
        binding.layoutWallet.buttonAddOperation.text = getString(R.string.add_operation_text)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureToolbar()
        initLayout()
        initWalletRecycler()
        setupButtonListener()
//        viewModel.transactionList.observe(viewLifecycleOwner) {
//            if (viewModel.transactionList.value!!.size == 0)
//                binding.layoutWallet.noEntries.visibility = View.VISIBLE
//            else binding.layoutWallet.noEntries.visibility = View.GONE
//        }
    }

    override fun configureToolbar() {
        setHasOptionsMenu(true)
        activity?.let { activity ->
            (activity as ToolbarHolder).setToolbarTitle("")
        }
    }

    private fun initWalletRecycler() {
        initAdapter()
        binding.layoutWallet.walletRecycle.setHasFixedSize(true)
        binding.layoutWallet.walletRecycle.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = baseAdapter
        }
        submitAdapterList()
        addDecorators()
    }

    private fun initAdapter() {
        baseAdapter = BaseAdapter()
        baseAdapter?.addDelegate(WalletTransactionDelegate{ _, action ->
            when (action.actionId) {
                R.id.edit -> Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show()
                R.id.delete -> {
                    val deleteDialog = DeleteDialogFragment()
                    val manager = activity?.supportFragmentManager
                    manager?.let { deleteDialog.show(it, getString(R.string.delete_dialog_tag)) }
                }
            }
        })
        baseAdapter?.addDelegate(DateDelegate())
    }

    private fun submitAdapterList() {
        // TODO - перенести обработку во вью модель
         context?.let { context ->
             baseAdapter?.submitList(viewModel.transactionList.toDelegateItemListWithDate(context))
         }
    }

    private fun addDecorators() {
        binding.layoutWallet.walletRecycle.addItemDecoration(
            DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
                .apply {
                    ResourcesCompat.getDrawable(resources, R.drawable.item_divider, context?.theme)
                        ?.let { drawable ->
                            setDrawable(
                                drawable
                            )
                        }
                }
        )
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
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
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
        baseAdapter = null
    }
}