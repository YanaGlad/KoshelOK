package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gladkikhvlasovtinkoff.MainActivity
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentSelectTransactionCategoryBinding
import com.example.gladkikhvlasovtinkoff.databinding.FragmentWalletsBinding
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarFragment
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder

class WalletsFragment : Fragment() {
    private var _binding: FragmentWalletsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalletsBinding.inflate(inflater)
        (activity as MainActivity).supportActionBar?.hide()

        initLayout()
        binding.layoutWallet.buttonAddOperation.setOnClickListener {
            val action = WalletsFragmentDirections.actionWalletsFragmentToOptionFragment()
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

    override fun onDestroy() {
        super.onDestroy()

    }

}