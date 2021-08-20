package com.example.gladkikhvlasovtinkoff.ui.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gladkikhvlasovtinkoff.databinding.FragmentWelcomeBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    private val loginResultHandler = registerLoginResultHandler()

    private fun registerLoginResultHandler() =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
            initAccount(result)
        }

    private fun initAccount(result: ActivityResult?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(result?.data)
        if (task.isSuccessful) {
            val account = task.result
            navigateToWallets(account)
        }
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

    override fun onStart() {
        super.onStart()

        val account = GoogleSignIn.getLastSignedInAccount(requireContext())

        navigateToWallets(account)
    }

    private fun getSignInIntent(): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        return mGoogleSignInClient.signInIntent
    }

    private fun navigateToWallets(account: GoogleSignInAccount?) {
        if (account != null) {
            val navController = findNavController()
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToWalletsFragment()
            navController.navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}