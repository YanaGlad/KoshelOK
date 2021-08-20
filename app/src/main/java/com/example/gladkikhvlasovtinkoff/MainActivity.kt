package com.example.gladkikhvlasovtinkoff

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

    private fun setupToolbar(){
        binding.toolBar.title = ""
        binding.toolBar.inflateMenu(R.menu.menu_main)
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.toolBar.setNavigationOnClickListener { onBackPressed() }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            Toast.makeText(applicationContext, "Settings", Toast.LENGTH_SHORT).show()
        }
        return true
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

}

