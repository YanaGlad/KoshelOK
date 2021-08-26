package com.example.gladkikhvlasovtinkoff.ui.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.gladkikhvlasovtinkoff.databinding.FragmentWelcomeBinding
import com.example.gladkikhvlasovtinkoff.model.WalletDataSample
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel : WelcomeViewModel by viewModels()

    private val loginResultHandler = registerLoginResultHandler()

    private fun registerLoginResultHandler() =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
            initAccount(result)
        }

    private fun initAccount(result: ActivityResult?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(result?.data)
        // TODO else обработка ошибки, нужно что-то показывать пользователю
        if (task.isSuccessful) {
            val account = task.result
            if(account != null) {
                viewModel.logInWithAccount(account)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.authButton.setOnClickListener {
            loginResultHandler.launch(getSignInIntent())
        }
        viewModel.viewState.observe(viewLifecycleOwner){
            handleViewState(it)
        }
    }

    private fun handleViewState(viewState: AuthViewState) =
            when (viewState) {
                is AuthViewState.SuccessLogin -> navigateToWallets()
                is AuthViewState.Loading -> setLoading()
                is AuthViewState.Error.NetworkError -> showNetworkError()
                is AuthViewState.Error.UnexpectedError -> showUnexpectedError()
            }

    private fun showUnexpectedError() {
        setLoaded()
    }

    private fun showNetworkError() {
        setLoaded()
    }

    private fun setLoading() {
        binding.authProgressBar.visibility = View.VISIBLE
        binding.authButton.isEnabled = false
    }

    private fun setLoaded(){
        binding.authProgressBar.visibility = View.GONE
        binding.authButton.isEnabled = true
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(requireContext())
        // TODO else обработка ошибки
        // account?.let { viewModel.logInWithAccount(account) } ?: todo
        if(account != null) {
            viewModel.logInWithAccount(account)
        }
    }

    private fun getSignInIntent(): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        return mGoogleSignInClient.signInIntent
    }

    private fun navigateToWallets() {
        val navController = findNavController()

        val action = WelcomeFragmentDirections.actionWelcomeFragmentToWalletsFragment()
        navController.navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
