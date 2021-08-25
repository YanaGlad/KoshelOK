package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gladkikhvlasovtinkoff.MainActivity
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentWalletsBinding
import com.example.gladkikhvlasovtinkoff.extension.exhaustive
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.model.WalletDataSample
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder
import com.example.gladkikhvlasovtinkoff.ui.ui.transtaction.DeleteDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalletsFragment : ToolbarFragment(), DeleteHelper<WalletData> {
    private val viewModel: WalletsViewModel by viewModels()
    private val args: WalletsFragmentArgs by navArgs()

    private var _binding: FragmentWalletsBinding? = null
    private val binding get() = _binding!!
    private var expanded = false
    private var walletsAdapter: WalletsAdapter? = null
    private var walletsHiddenAdapter: WalletsAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity
            ?.onBackPressedDispatcher
            ?.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.finish()
                }
            })
        handleArguments(args.walletData)
    }

    private fun handleArguments(walletData: WalletDataSample?) {
        walletData?.let { walletData ->
            viewModel.addWallet(walletData)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalletsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.hide()
        initLayout()
        initRecycler()
        expandRecyclerAnimation()
        setupNavigation()

        viewModel.viewState.observe(viewLifecycleOwner) {
            handleViewState(it)
        }
    }

    private fun handleViewState(viewState: WalletListViewState?) {
        when (viewState) {
            is WalletListViewState.Loaded -> {
 
                val list : MutableList<WalletData> = ArrayList()
                val listHidden : MutableList<WalletData> = ArrayList()

                for (i in viewState.list.indices){
                    if(!viewState.list[i].hidden)
                        list.add(viewState.list[i])
                    else  listHidden.add(viewState.list[i])
                }

                walletsAdapter?.submitList(list)
                walletsHiddenAdapter?.submitList(listHidden)
 
                binding.noOperationMessage.visibility =
                    if (viewState.list.isEmpty()) View.VISIBLE else View.GONE
            }
            else -> {
            }
        }
        binding.layoutWallet.walletRecycle.adapter = walletsAdapter
 
        binding.layoutWallet.hiddenWalletRecycle.adapter = walletsHiddenAdapter
        binding.skeletonWallet.showOriginal()

 
    }

    private fun setupNavigation() {
        binding.layoutWallet.buttonAddOperation.setOnClickListener {
            val action = WalletsFragmentDirections.actionWalletsFragmentToEnterWalletNameFragment(
                WalletDataSample()
            )
            findNavController().navigate(action)
            (activity as MainActivity).supportActionBar?.show()
        }
    }


    private fun initRecycler() {
        walletsAdapter = WalletsAdapter(
            object : WalletsAdapter.OnWalletClickListener {
                override fun onWalletClick(walletData: WalletData, position: Int) {
                    navigateToWallet(
                        WalletDataSample(
                            walletData.id,
                            walletData.username,
                            walletData.name,
                            walletData.limit,
                            walletData.amount,
                            walletData.currency
                        ), position
                    )
                }
            }
        )
        { walletData, action ->
            when (action.actionId) {
                R.id.hide -> {
                    val data = walletData.toWalletDataSample()
                    data.hidden = !data.hidden
                    viewModel.updateWallet(data)
                }
                R.id.edit -> {
                    val action = WalletsFragmentDirections.actionWalletsFragmentToNewWalletFragment(
                        walletData.toWalletDataSample(),
                        true
                    )
                    findNavController().navigate(action)
                }
                R.id.delete -> {
                    val deleteDialog = DeleteDialogFragment<WalletData>(this, walletData)
                    val manager = activity?.supportFragmentManager
                    manager?.let {
                        deleteDialog.show(
                            it,
                            getString(R.string.delete_dialog_tag)
                        )
                    }
                    //  walletsAdapter!!.notifyDataSetChanged()
                }
            }
        }

        walletsHiddenAdapter = WalletsAdapter(
            object : WalletsAdapter.OnWalletClickListener {
                override fun onWalletClick(walletData: WalletData, position: Int) {
                    navigateToWallet(
                        WalletDataSample(
                            walletData.id,
                            walletData.username,
                            walletData.name,
                            walletData.limit,
                            walletData.amount,
                            walletData.currency
                        ), position
                    )
                }
            }
        )
        { walletData, action ->
            when (action.actionId) {
                R.id.hide -> {
                    val data = walletData.toWalletDataSample()
                    data.hidden = !data.hidden
                    viewModel.updateWallet(data)
                }
                R.id.edit -> {
                    val action = WalletsFragmentDirections.actionWalletsFragmentToNewWalletFragment(
                        walletData.toWalletDataSample(),
                        true
                    )
                    findNavController().navigate(action)
                }
                R.id.delete -> {
                    val deleteDialog = DeleteDialogFragment<WalletData>(this, walletData)
                    val manager = activity?.supportFragmentManager
                    manager?.let {
                        deleteDialog.show(
                            it,
                            getString(R.string.delete_dialog_tag)
                        )
                    }
                    //  walletsAdapter!!.notifyDataSetChanged()
                }
            }
        }

        binding.layoutWallet.walletRecycle.setHasFixedSize(true)
        binding.layoutWallet.walletRecycle.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = walletsAdapter
        }

        binding.layoutWallet.hiddenWalletRecycle.setHasFixedSize(true)
        binding.layoutWallet.hiddenWalletRecycle.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = walletsHiddenAdapter
        }
    }

    private fun navigateToWallet(walletData: WalletDataSample, position: Int) {
        val action = WalletsFragmentDirections.actionWalletsFragmentToOptionFragment(
            walletData = walletData
        )
        findNavController().navigate(action)
    }

    private fun expandRecyclerAnimation() {
        binding.layoutWallet.showMore.setOnClickListener {
            expandRecyclerView()
        }
        binding.layoutWallet.down.setOnClickListener {
            expandRecyclerView()
        }
    }

    //TODO create extension accepting lambda
    private fun expandRecyclerView() {
        expanded = if (expanded) {
            binding.layoutWallet.motionLayout.transitionToStart()
            binding.layoutWallet.showMore.text = getString(R.string.show_more)
            binding.layoutWallet.down.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_down_expand
                )
            )
            false
        } else {
            binding.layoutWallet.motionLayout.transitionToEnd()
            binding.layoutWallet.showMore.text = getString(R.string.hide_show_more)
            binding.layoutWallet.down.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_up_expand
                )
            )
            true
        }
    }

    private fun initLayout() {
        binding.layoutWallet.info.text = getString(R.string.total_sum)
        binding.layoutWallet.info.setTextColor(Color.WHITE)
        binding.layoutWallet.walletBalance.setTextColor(Color.WHITE)
        binding.layoutWallet.income.incomeText.text = getString(R.string.total_income)
        binding.layoutWallet.expenditure.expenditureText.text =
            getString(R.string.total_expenditure)
        binding.layoutWallet.buttonAddOperation.text = getString(R.string.create_wallet)
        binding.skeletonWallet.showSkeleton()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.default_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }

    override fun configureToolbar() {
        activity?.let { activity ->
            (activity as ToolbarHolder).setToolbarTitle(getString(R.string.setup_wallet_name))
        }
    }

    override fun delete(pos: WalletData) {
        viewModel.deleteWallet(pos)
    }
}
