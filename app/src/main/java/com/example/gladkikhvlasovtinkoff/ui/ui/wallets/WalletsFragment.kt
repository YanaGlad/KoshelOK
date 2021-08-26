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

    private var isClickedExpense = false
    private var isClickedIncome = false

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
        walletData?.let { walletDataSample ->
            viewModel.addWallet(walletDataSample)
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
        configureToolbar()

        viewModel.viewState.observe(viewLifecycleOwner) {
            handleViewState(it)
        }
        viewModel.getAllWalletsBalance("USD", args.walletData.username)

        initLayout()
        initRecycler()
        expandRecyclerAnimation()
        setupNavigation()
    }


    private fun handleViewState(viewState: WalletListViewState?) {
        binding.skeletonWallet.showSkeleton()
        when (viewState) {
            is WalletListViewState.Loaded -> {
                onListLoaded(viewState)
            }
            is WalletListViewState.LoadedString -> {
                balanceLoaded(viewState)
            }
            is WalletListViewState.Loading -> {
                onLoading()
            }
            is WalletListViewState.Error.NetworkError -> {
                onNetworkError()
            }
            is WalletListViewState.SuccessOperation -> onLoaded()
            is WalletListViewState.Error.UnexpectedError -> {
                onUnexpectedError()
            }
        }
        walletsAdapter?.notifyDataSetChanged()
        walletsHiddenAdapter?.notifyDataSetChanged()
        binding.layoutWallet.walletRecycle.adapter = walletsAdapter
        binding.layoutWallet.hiddenWalletRecycle.adapter = walletsHiddenAdapter

    }

    private fun onUnexpectedError() {
        onLoaded()
        val layout: View = LayoutInflater.from(context).inflate(
            R.layout.something_went_wrong_toast, binding.root, false
        )
        val toast = Toast(context)
        toast.setGravity(Gravity.TOP, 0, 40)
        toast.duration = Toast.LENGTH_LONG
        toast.setView(layout)
        toast.show()
    }

    private fun onLoading() {
        binding.skeletonWallet.showSkeleton()
    }

    private fun onNetworkError() {
        onLoaded()
        val layout: View = LayoutInflater.from(context).inflate(
            R.layout.network_error_toast, binding.root, false
        )
        val toast = Toast(context)
        toast.setGravity(Gravity.TOP, 0, 40)
        toast.duration = Toast.LENGTH_LONG
        toast.setView(layout)
        toast.show()
    }

    private fun onLoaded() {
        binding.skeletonWallet.showOriginal()
    }

    private fun balanceLoaded(viewState: WalletListViewState.LoadedString) {
        binding.layoutWallet.walletBalance.text = viewState.result
    }

    private fun onListLoaded(viewState: WalletListViewState.Loaded) {
        val list: MutableList<WalletData> = ArrayList()
        val listHidden: MutableList<WalletData> = ArrayList()
        for (i in viewState.list.indices) {
            if (!viewState.list[i].hidden)
                list.add(viewState.list[i])
            else listHidden.add(viewState.list[i])
        }
        walletsAdapter?.submitList(list)
        walletsHiddenAdapter?.submitList(listHidden)

        binding.noOperationMessage.visibility =
            if (viewState.list.isEmpty()) View.VISIBLE else View.GONE
        if (viewState.list.isEmpty()) {
            binding.layoutWallet.showMore.visibility = View.GONE
            binding.layoutWallet.down.visibility = View.GONE
        } else {
            binding.layoutWallet.showMore.visibility = View.VISIBLE
            binding.layoutWallet.down.visibility = View.VISIBLE
        }

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
        walletsAdapter = initAdapter()
        walletsHiddenAdapter = initAdapter()

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

    private fun initAdapter() = WalletsAdapter(
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
                    )
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
                walletsAdapter?.notifyDataSetChanged()
                walletsHiddenAdapter?.notifyDataSetChanged()
            }
            R.id.edit -> {
                val navDirection =
                    WalletsFragmentDirections.actionWalletsFragmentToNewWalletFragment(
                        walletData.toWalletDataSample(),
                        true
                    )
                findNavController().navigate(navDirection)
            }
            R.id.delete -> {
                val deleteDialog = DeleteDialogFragment(this, walletData)
                val manager = activity?.supportFragmentManager
                manager?.let {
                    deleteDialog.show(
                        it,
                        getString(R.string.delete_dialog_tag)
                    )
                }
            }
        }
    }

    private fun navigateToWallet(walletData: WalletDataSample) {
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
            binding.layoutWallet.showMore.text = getString(R.string.hidden_wallets)
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
        binding.skeletonWallet.showSkeleton()
        binding.layoutWallet.info.text = getString(R.string.total_sum)
        binding.layoutWallet.info.setTextColor(Color.WHITE)
        binding.layoutWallet.walletBalance.setTextColor(Color.WHITE)
        binding.layoutWallet.income.incomeText.text = getString(R.string.total_income)
        binding.layoutWallet.expenditure.expenditureText.text =
            getString(R.string.total_expenditure)
        binding.layoutWallet.buttonAddOperation.text = getString(R.string.create_wallet)
        binding.layoutWallet.income.cardIncome.setOnClickListener {
            if (!isClickedIncome) {
                binding.layoutWallet.income.cardIncome.setCardBackgroundColor(resources.getColor(R.color.clicked_card))
            } else binding.layoutWallet.income.cardIncome.setCardBackgroundColor(
                resources.getColor(
                    R.color.purple
                )
            )
            isClickedIncome = !isClickedIncome
        }
        binding.layoutWallet.expenditure.cardExpense.setOnClickListener {
            if (!isClickedExpense)
                binding.layoutWallet.expenditure.cardExpense.setCardBackgroundColor(
                    resources.getColor(
                        R.color.clicked_card
                    )
                )
            else binding.layoutWallet.expenditure.cardExpense.setCardBackgroundColor(
                resources.getColor(
                    R.color.purple
                )
            )
            isClickedExpense = !isClickedExpense
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.default_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }

    override fun configureToolbar() {
        activity?.let { activity ->
            (activity as MainActivity).hideToolbar()
        }
    }

    override fun delete(pos: WalletData) {
        viewModel.deleteWallet(pos)
        walletsAdapter?.notifyDataSetChanged()
        walletsHiddenAdapter?.notifyDataSetChanged()

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }
}
