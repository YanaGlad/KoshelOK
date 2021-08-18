package com.example.gladkikhvlasovtinkoff

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gladkikhvlasovtinkoff.databinding.FragmentOptionBinding
import com.example.gladkikhvlasovtinkoff.databinding.FragmentWelcomeBinding
import java.util.*


class OptionFragment : Fragment() {

    private var _binding: FragmentOptionBinding? = null
    private val binding get() = _binding!!
    private var timer = EasyTimer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        timer.stopTimer()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOptionBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true);

        binding.toolBar.title = ""

        (activity as AppCompatActivity).setSupportActionBar(binding.toolBar);
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayShowHomeEnabled(true);

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu,inflater);
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