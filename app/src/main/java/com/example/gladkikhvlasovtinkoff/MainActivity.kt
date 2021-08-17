package com.example.gladkikhvlasovtinkoff

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.gladkikhvlasovtinkoff.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    private val loginResultHandler =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->

            val task = GoogleSignIn.getSignedInAccountFromIntent(result?.data)
            val account = task.result

            startSecondActivity(account)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        binding.btnPush.setOnClickListener {
            loginResultHandler.launch(getSignInIntent())
        }
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        val account = GoogleSignIn.getLastSignedInAccount(this)
        startSecondActivity(account)
    }

    private fun getSignInIntent(): Intent {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        return mGoogleSignInClient.signInIntent
    }

    private fun startSecondActivity(account: GoogleSignInAccount?) {
        if (account != null) {
            binding.btnPush.visibility = View.INVISIBLE

            // start second activity
        }
    }

}
