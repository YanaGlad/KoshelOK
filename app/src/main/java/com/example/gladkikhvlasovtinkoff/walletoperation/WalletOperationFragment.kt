package com.example.gladkikhvlasovtinkoff.walletoperation

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gladkikhvlasovtinkoff.EasyTimer
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentWalletOperationBinding
import com.example.gladkikhvlasovtinkoff.extension.MILLIS_IN_DAY
import com.example.gladkikhvlasovtinkoff.util.ItemTouchHelperCallback
import com.example.gladkikhvlasovtinkoff.util.styleText


class WalletOperationFragment : Fragment() {

    private val viewModel : WalletOperationViewModel by viewModels()

    private var _binding: FragmentWalletOperationBinding? = null
    private val binding get() = _binding!!
    private var timer = EasyTimer()

    private lateinit var adapter: WalletOperationAdapter
    private lateinit var adapterEdit: EditAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        timer.stopTimer()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalletOperationBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true);

        initWalletRecycler(binding.editRecycle)
        initWalletRecycler(binding.walletRecycle)

        val list = ArrayList<WalletOperationModel>()

        list.add(
            WalletOperationModel(
                System.currentTimeMillis(),
                 R.drawable.ic_capitalisation,
                R.string.supermarket,
                "Траты",
                "-12000"
            )
        )

        list.add(
            WalletOperationModel(
                System.currentTimeMillis() - MILLIS_IN_DAY,
                 R.drawable.ic_salary,
                R.string.salary,
                "Пополнение",
                "130000"
            )
        )

        adapter = WalletOperationAdapter()
        binding.walletRecycle.adapter = adapter

        val callback = ItemTouchHelperCallback(adapter)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.walletRecycle)

        adapter.submitList(list)
        val navController = findNavController()

        binding.buttonAddOperation.setOnClickListener {
            val action = WalletOperationFragmentDirections.actionOptionFragmentToFragmentSelectOperationValue()
            navController.navigate(action)
        }

        return binding.root
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}