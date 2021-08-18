package com.example.gladkikhvlasovtinkoff

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gladkikhvlasovtinkoff.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn

class MainActivity : AppCompatActivity(R.layout.activity_main){

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

