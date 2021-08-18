package com.example.gladkikhvlasovtinkoff

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View

import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import com.example.gladkikhvlasovtinkoff.databinding.ActivityMainBinding
import com.example.gladkikhvlasovtinkoff.databinding.FragmentOptionBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
       // _binding = ActivityMainBinding.inflate(layoutInflater)

        //setSupportActionBar(binding.toolBar);

        val toolbar = findViewById(R.id.toolBar) as Toolbar
        setSupportActionBar(toolbar);

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

