package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.gladkikhvlasovtinkoff.MainActivity
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentWalletsBinding
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder

class WalletsFragment : ToolbarFragment() {
    private val viewModel : WalletsViewModel by viewModels()

    private var _binding: FragmentWalletsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.finish()
                } })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalletsBinding.inflate(inflater)
        (activity as MainActivity).supportActionBar?.hide()



        viewModel.walletList.observe(viewLifecycleOwner){
            binding.noOperationMessage.visibility = if(viewModel.walletList.value!!.size == 0) View.VISIBLE else View.GONE
        }

        initLayout()

        binding.layoutWallet.buttonAddOperation.setOnClickListener {
            val action = WalletsFragmentDirections.actionWalletsFragmentToEnterWalletNameFragment()
            findNavController().navigate(action)
            (activity as MainActivity).supportActionBar?.show()
        }


        return binding.root
    }

    private fun initLayout() {
        binding.layoutWallet.info.text = getString(R.string.total_sum)
        binding.layoutWallet.info.setTextColor(Color.WHITE)
        binding.layoutWallet.walletBalance.setTextColor(Color.WHITE)
        binding.layoutWallet.income.incomeText.text = getString(R.string.total_income)
        binding.layoutWallet.expenditure.expenditureText.text =
            getString(R.string.total_expenditure)
        binding.layoutWallet.buttonAddOperation.text = getString(R.string.create_wallet)
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

    override fun onDestroy() {
        super.onDestroy()
    }

}