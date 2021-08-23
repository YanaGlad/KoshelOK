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
import com.example.gladkikhvlasovtinkoff.model.WalletDataSample
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder
import com.example.gladkikhvlasovtinkoff.ui.ui.transtaction.DeleteDialogFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.transtaction.WalletTransactionFragmentArgs

class WalletsFragment : ToolbarFragment() {
    private val viewModel: WalletsViewModel by viewModels()

    private var _binding: FragmentWalletsBinding? = null
    private val binding get() = _binding!!
    private var expanded = false
    private var operationsAdapter: WalletsAdapter? = null
    private var operationsHiddenAdapter: WalletsAdapter? = null

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
        (activity as MainActivity).supportActionBar?.hide()
        initLayout()
        initRecycler()
        expandRecyclerAnimation()
        setupNavigation()
    }

    private fun setupNavigation() {
        binding.layoutWallet.buttonAddOperation.setOnClickListener {
            val action = WalletsFragmentDirections.actionWalletsFragmentToEnterWalletNameFragment(WalletDataSample())
            findNavController().navigate(action)
            (activity as MainActivity).supportActionBar?.show()
        }
    }


    private fun initRecycler() {
        operationsAdapter = WalletsAdapter(requireContext()) { _, action ->
            when (action.actionId) {
                R.id.hide -> Toast.makeText(context, "Hide", Toast.LENGTH_SHORT).show()
                R.id.edit -> Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show()
                R.id.delete -> {
                    val deleteDialog = DeleteDialogFragment()
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

        operationsHiddenAdapter = WalletsAdapter(requireContext()) { _, action ->
            when (action.actionId) {
                R.id.hide -> Toast.makeText(context, "Hide", Toast.LENGTH_SHORT).show()
                R.id.edit -> Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show()
                R.id.delete -> {
                    val deleteDialog = DeleteDialogFragment()
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

        binding.layoutWallet.walletRecycle.setHasFixedSize(true)
        binding.layoutWallet.walletRecycle.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = operationsAdapter
        }
        operationsAdapter?.submitList(viewModel.walletList.value)

        binding.layoutWallet.hiddenWalletRecycle.setHasFixedSize(true)
        binding.layoutWallet.hiddenWalletRecycle.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = operationsHiddenAdapter
        }

        operationsHiddenAdapter?.submitList(viewModel.hiddenWalletList.value)


        viewModel.walletList.observe(viewLifecycleOwner) {
            binding.noOperationMessage.visibility =
                if (viewModel.walletList.value!!.size == 0) View.VISIBLE else View.GONE
        }

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

        binding.layoutWallet.showMore.setOnClickListener {

        }
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
}
