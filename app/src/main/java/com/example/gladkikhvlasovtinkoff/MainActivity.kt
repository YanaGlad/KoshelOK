package com.example.gladkikhvlasovtinkoff

import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.gladkikhvlasovtinkoff.databinding.ActivityMainBinding
import com.example.gladkikhvlasovtinkoff.ui.ui.toolbar.ToolbarHolder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ToolbarHolder {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.popBackStack()
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setupToolbar()
    }


    private fun setupToolbar() {
        binding.toolBar.title = ""
        binding.toolBar.inflateMenu(R.menu.menu_main)
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        binding.toolBar.setNavigationOnClickListener { onBackPressed() }
    }


    override fun onBackPressed() {
        val count: Int = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    override fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun setToolbarNavigationButtonIcon(resourceId: Int) {
        supportActionBar?.setHomeAsUpIndicator(resourceId)
        supportActionBar?.hide()
    }

    override fun hideToolbar() {
        supportActionBar?.hide()
    }

    override fun showToolbar() {
        supportActionBar?.show()
    }

}

