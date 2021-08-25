package com.example.gladkikhvlasovtinkoff.ui.ui.transtaction

import android.annotation.SuppressLint
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
import com.example.gladkikhvlasovtinkoff.MainActivity
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentWalletTransactionBinding
import com.example.gladkikhvlasovtinkoff.extension.convertCurrencyCodeToSymbol
import com.example.gladkikhvlasovtinkoff.extension.setupNavigation
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionSample
import com.example.gladkikhvlasovtinkoff.ui.ui.delegates.BaseAdapter
import com.example.gladkikhvlasovtinkoff.ui.ui.delegates.DateDelegate
import com.example.gladkikhvlasovtinkoff.ui.ui.delegates.WalletTransactionDelegate
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.DeleteHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalletTransactionFragment : ToolbarFragment(), DeleteHelper<WalletTransactionModel> {

    private val viewModel: WalletTransactionViewModel by viewModels()
    private val args: WalletTransactionFragmentArgs by navArgs()

    private var _binding: FragmentWalletTransactionBinding? = null
    private val binding get() = _binding!!

    private var baseAdapter: BaseAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).supportActionBar?.show()
        setupNavigation(
            fragment = this,
            activity = activity as AppCompatActivity,
            navController = findNavController(),
            action = WalletTransactionFragmentDirections.actionOptionFragmentToWalletsFragment()
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalletTransactionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun initLayout() {
        if(args.walletData != null){
            binding.layoutWallet.walletBalance.text = args.walletData!!.amount + " " +
                    args.walletData!!.currency.code.convertCurrencyCodeToSymbol()
            binding.layoutWallet.info.text = args.walletData!!.name
        }
        binding.layoutWallet.hiddenWalletRecycle.visibility = View.GONE
        binding.layoutWallet.down.visibility = View.GONE
        binding.layoutWallet.showMore.visibility = View.GONE
        binding.layoutWallet.income.incomeText.text = getString(R.string.income_text)
        binding.layoutWallet.expenditure.expenditureText.text = getString(R.string.costs_text)
        binding.layoutWallet.buttonAddOperation.text = getString(R.string.add_operation_text)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initLayout()
        initWalletRecycler()
        setupButtonListener()
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            handleViewState(viewState)
        }
        args.walletData?.id?.let{ username ->
            viewModel.getTransactionListByWalletId(username)
        }
    }

    private fun handleViewState(viewState: TransactionListViewState) {
        when(viewState){
            is TransactionListViewState.Loaded ->
                baseAdapter?.submitList(viewState.list)
            else -> {}
        }
        binding.layoutWallet.walletRecycle.adapter = baseAdapter
    }

    override fun configureToolbar() {
        setHasOptionsMenu(true)
        activity?.let { activity ->
            (activity as ToolbarHolder).setToolbarTitle("")
        }
    }

    override fun onResume() {
        super.onResume()
        configureToolbar()
    }

    private fun initWalletRecycler() {
        initAdapter()
        binding.layoutWallet.walletRecycle.setHasFixedSize(true)
        binding.layoutWallet.walletRecycle.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = baseAdapter
        }
        addDecorators()
    }

    private fun initAdapter() {
        baseAdapter = BaseAdapter()
        baseAdapter?.addDelegate(WalletTransactionDelegate{ _, action ->
            when (action.actionId) {
                R.id.edit -> Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show()
                R.id.delete -> {
                    val deleteDialog = DeleteDialogFragment(this)
                    val manager = activity?.supportFragmentManager
                    manager?.let { deleteDialog.show(it, getString(R.string.delete_dialog_tag)) }
                }
            }
        })
        baseAdapter?.addDelegate(DateDelegate())
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
                    WalletTransactionSample(), args.walletData!!
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
            val action = WalletTransactionFragmentDirections.actionOptionFragmentToNewWalletFragment(
                args.walletData!!, true)
            findNavController().navigate(action)
        }
        return true
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        baseAdapter = null
    }

    override fun delete(pos: WalletTransactionModel) {
        TODO("Not yet implemented")
    }
}