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

//        binding.toolBar.title = ""
//        binding.toolBar.inflateMenu(R.menu.default_toolbar)
//        setSupportActionBar(binding.toolBar);
//        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar()?.setDisplayShowHomeEnabled(true);

    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.action_settings) {
//            Toast.makeText(applicationContext, "Settings", Toast.LENGTH_SHORT).show()
//        }
//        return true
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

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

