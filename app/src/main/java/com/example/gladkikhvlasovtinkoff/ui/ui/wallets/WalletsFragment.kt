package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gladkikhvlasovtinkoff.MainActivity
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentWalletsBinding
import com.example.gladkikhvlasovtinkoff.model.CurrencyCourse
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.model.WalletDataSample
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.transtaction.DeleteDialogFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.viewmodel.WalletsViewModel
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.CoursesPlateViewState
import com.example.gladkikhvlasovtinkoff.ui.ui.viewstate.WalletListViewState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WalletsFragment : ToolbarFragment(), DeleteHelper<WalletData> {
    private val viewModel: WalletsViewModel by viewModels()

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

        initLayout()
        initRecycler()
        expandRecyclerAnimation()
        setupNavigation()

        viewModel.viewState.observe(viewLifecycleOwner) {
            handleViewState(it)
        }
        viewModel.coursesViewState.observe(viewLifecycleOwner){
            handleCoursesPlateViewState(viewState = it)
        }
        binding.skeletonWallet.showOriginal()
        onCoursesLoading()
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

    private fun handleCoursesPlateViewState(viewState: CoursesPlateViewState){
        when(viewState){
            is CoursesPlateViewState.Error -> onCoursesError()
            is CoursesPlateViewState.Loading -> onCoursesLoading()
            is CoursesPlateViewState.Loaded -> setupCoursesPlate(viewState.currencyCourses)
        }
    }

    private fun setupCoursesPlate(currencyCourses: List<CurrencyCourse>) {
        binding.layoutWallet.coursesPlate.visibility = View.VISIBLE
        if(currencyCourses.size == 3){
            binding.layoutWallet.firstCurrencyProgressBar.visibility = View.GONE
            binding.layoutWallet.secondCurrencyProgressBar.visibility = View.GONE
            binding.layoutWallet.thirdCurrencyProgressBar.visibility = View.GONE
            binding.layoutWallet.firstCurrencyCode.visibility = View.VISIBLE
            binding.layoutWallet.firstCurrencyCourse.visibility = View.VISIBLE
            binding.layoutWallet.firstCurrencyStatus.visibility = View.VISIBLE
            binding.layoutWallet.firstCurrencyCode.text = currencyCourses[0].code
            binding.layoutWallet.firstCurrencyCourse.text = currencyCourses[0].course
            binding.layoutWallet.firstCurrencyStatus.setCourseStatusIcon(currencyCourses[0].isUp)
            binding.layoutWallet.secondCurrencyCode.visibility = View.VISIBLE
            binding.layoutWallet.secondCurrencyStatus.visibility = View.VISIBLE
            binding.layoutWallet.secondCurrencyCourse.visibility = View.VISIBLE
            binding.layoutWallet.secondCurrencyCode.text = currencyCourses[1].code
            binding.layoutWallet.secondCurrencyCourse.text = currencyCourses[1].course
            binding.layoutWallet.secondCurrencyStatus.setCourseStatusIcon(currencyCourses[1].isUp)
            binding.layoutWallet.thirdCurrencyCode.visibility = View.VISIBLE
            binding.layoutWallet.thirdCurrencyCourse.visibility = View.VISIBLE
            binding.layoutWallet.thirdCurrencyStatus.visibility = View.VISIBLE
            binding.layoutWallet.thirdCurrencyCode.text = currencyCourses[2].code
            binding.layoutWallet.thirdCurrencyCourse.text = currencyCourses[2].course
            binding.layoutWallet.thirdCurrencyStatus.setCourseStatusIcon(currencyCourses[2].isUp)
        }else
            onCoursesError()
    }

    private fun onCoursesLoading() {
        binding.layoutWallet.firstCurrencyCode.visibility = View.GONE
        binding.layoutWallet.firstCurrencyCourse.visibility = View.GONE
        binding.layoutWallet.firstCurrencyStatus.visibility = View.GONE
        binding.layoutWallet.secondCurrencyCode.visibility = View.GONE
        binding.layoutWallet.secondCurrencyStatus.visibility = View.GONE
        binding.layoutWallet.secondCurrencyCourse.visibility = View.GONE
        binding.layoutWallet.thirdCurrencyCode.visibility = View.GONE
        binding.layoutWallet.thirdCurrencyCourse.visibility = View.GONE
        binding.layoutWallet.thirdCurrencyStatus.visibility = View.GONE
        binding.layoutWallet.firstCurrencyProgressBar.visibility = View.VISIBLE
        binding.layoutWallet.secondCurrencyProgressBar.visibility = View.VISIBLE
        binding.layoutWallet.thirdCurrencyProgressBar.visibility = View.VISIBLE
    }

    private fun onCoursesError() {
        binding.layoutWallet.coursesPlate.visibility = View.GONE
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
        initWalletsUi()
        initOnPlatesClickListeners()

    }

    private fun initWalletsUi() {
        binding.skeletonWallet.showSkeleton()
        binding.layoutWallet.info.text = getString(R.string.total_sum)
        binding.layoutWallet.info.setTextColor(Color.WHITE)
        binding.layoutWallet.walletBalance.setTextColor(Color.WHITE)
        binding.layoutWallet.income.incomeText.text = getString(R.string.total_income)
        binding.layoutWallet.expenditure.expenditureText.text =
            getString(R.string.total_expenditure)
        binding.layoutWallet.buttonAddOperation.text = getString(R.string.create_wallet)
    }

    private fun initOnPlatesClickListeners(){
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

    private fun ImageView.setCourseStatusIcon(isUp : Boolean){
        this.setImageDrawable(
            if(isUp)
                ResourcesCompat.getDrawable(resources, R.drawable.ic_up_expand, context.theme )
            else
                ResourcesCompat.getDrawable(resources, R.drawable.ic_down_expand, context.theme)
        )
    }
}
