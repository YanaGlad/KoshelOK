package com.example.gladkikhvlasovtinkoff.walletoperation

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gladkikhvlasovtinkoff.EasyTimer
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.FragmentWalletOperationBinding


class WalletOperationFragment : Fragment() {

    private var _binding: FragmentWalletOperationBinding? = null
    private val binding get() = _binding!!
    private var timer = EasyTimer()

    private lateinit var adapter: WalletOperationAdapter

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

        binding.walletRecycle.setHasFixedSize(true)
        binding.walletRecycle.layoutManager = GridLayoutManager(context, 1)

        var list = ArrayList<WalletOperationModel>()

        list.add(
            WalletOperationModel(
                "Сегодня",
                "https://sun9-52.userapi.com/impg/NlasEB7KCldpBi0DjnauJEF-PUE0oEhu9HMkNQ/kWawbtuCio8.jpg?size=80x80&quality=96&sign=d836182efab03d81621cf85718b53bec&type=album",
                "Супермаркеты",
                "Траты",
                "-12 000",
                "15:00"
            )
        )

        list.add(
            WalletOperationModel(
                "Вчера",
                "https://sun9-30.userapi.com/impg/JqjVqMAJOvfeDRRKED7sjIEUctRs-nr9FbjcJg/0V62vbVETlU.jpg?size=80x80&quality=96&sign=86372ea563fc1a6e3960099bebb20186&type=album",
                "Зарплата",
                "Пополнение",
                "130 000",
                "13:13"
            )
        )

        adapter =
            WalletOperationAdapter(
                requireContext(),
                list
            )
        binding.walletRecycle.adapter = adapter

        binding.toolBar.title = ""

        (activity as AppCompatActivity).setSupportActionBar(binding.toolBar);
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayShowHomeEnabled(true)



        return binding.root
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

        binding.buttonAddOperation.setOnClickListener {
            if (!timer.timerDelay(3.0) && !timer.stop)
                super.requireActivity().onBackPressed()
            else timer.stopTimer()
            Toast.makeText(
                activity,
                "Нажмите еще раз в течение 3 сек чтобы выйти из приложения",
                Toast.LENGTH_SHORT
            ).show()

            timer.startTimer()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}