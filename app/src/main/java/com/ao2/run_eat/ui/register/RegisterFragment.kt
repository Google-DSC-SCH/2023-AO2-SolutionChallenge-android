package com.ao2.run_eat.ui.register

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ao2.run_eat.R
import com.ao2.run_eat.base.BaseFragment
import com.ao2.run_eat.databinding.FragmentRegisterBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>(R.layout.fragment_register) {

    private val TAG = "RegisterFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_register

    override val viewModel : RegisterViewModel by viewModels()
    private val navController by lazy { findNavController() }

    private var googleLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == 1) {
            val data: Intent? = result.data
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun initStartView() {
        binding.apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent

    }

    override fun initDataBinding() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.navigationHandler.collectLatest {
                when(it) {
                    is RegisterNavigationAction.NavigateToGoogleLogin -> googleLogin()
                }
            }
        }
    }

    override fun initAfterBinding() {
    }

    private fun googleLogin() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        val account = GoogleSignIn.getLastSignedInAccount(requireContext())

        val signInIntent = mGoogleSignInClient.signInIntent
        googleLauncher.launch(signInIntent)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val idToken = completedTask.getResult(ApiException::class.java).idToken
            idToken?.let { token ->
//                viewModel.oauthLogin(idToken = token, provider = "GOOGLE")
            }
        } catch (e: ApiException){
            toastMessage("구글 로그인에 실패 하였습니다.")
        }
    }


}