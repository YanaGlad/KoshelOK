package com.example.gladkikhvlasovtinkoff

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gladkikhvlasovtinkoff.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onBackPressed() {
        val count: Int = getSupportFragmentManager().getBackStackEntryCount()
        if (count == 0) {
            super.onBackPressed()
            this.finish()
        } else {
            getSupportFragmentManager().popBackStack()
        }
    }
}

