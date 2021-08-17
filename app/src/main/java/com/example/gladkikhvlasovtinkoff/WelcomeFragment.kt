package com.example.gladkikhvlasovtinkoff

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gladkikhvlasovtinkoff.databinding.FragmentWelcomeBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!


    private val loginResultHandler =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->

            val task = GoogleSignIn.getSignedInAccountFromIntent(result?.data)
            val account = task.result

            startSecondActivity(account)
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWelcomeBinding.inflate(layoutInflater)

        binding.authButton.setOnClickListener {
            loginResultHandler.launch(getSignInIntent())
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onStart() {
        super.onStart()

        val account = GoogleSignIn.getLastSignedInAccount(context)
        startSecondActivity(account)
    }

    private fun getSignInIntent(): Intent {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(activity, gso)

        return mGoogleSignInClient.signInIntent
    }

    private fun startSecondActivity(account: GoogleSignInAccount?) {
        if (account != null) {
            val navController = findNavController()
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToOptionFragment()
            navController.navigate(action)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}