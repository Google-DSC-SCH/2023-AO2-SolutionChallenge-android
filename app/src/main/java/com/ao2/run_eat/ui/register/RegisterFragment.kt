package com.ao2.run_eat.ui.register

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ao2.run_eat.BuildConfig
import com.ao2.run_eat.R
import com.ao2.run_eat.base.BaseFragment
import com.ao2.run_eat.databinding.FragmentRegisterBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class RegisterFragment :
    BaseFragment<FragmentRegisterBinding, RegisterViewModel>(R.layout.fragment_register) {

    private val TAG = "RegisterFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_register

    override val viewModel: RegisterViewModel by viewModels()
    private val navController by lazy { findNavController() }

    private var googleLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(data)
                handleSignInResult(task)
            }
        }

    // 요청하고자 하는 권한들
    private val permissionList = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.POST_NOTIFICATIONS,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,

        )

    // 권한을 허용하도록 요청
    private val requestMultiplePermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
            results.forEach {
                Log.d("ttt key ", it.key)
                Log.d("ttt value", it.value.toString())

                if (!it.value) toastMessage("권한 허용 필요")
            }
        }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun initStartView() {
        binding.apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
        requestMultiplePermission.launch(permissionList)

        createNotification()

    }

    override fun initDataBinding() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.navigationHandler.collectLatest {
                when (it) {
                    is RegisterNavigationAction.NavigateToPushSetting -> {
                        if (!viewModel.notificationAgreed.value) {
//                            pushSettingDialog()
                            toastMessage("푸쉬 알림 전송 권한 없음")
                        } else {
                            viewModel.sendNotification()
                            toastMessage("푸쉬 알림 전송 완료")
                        }
                    }
                    is RegisterNavigationAction.NavigateToNotificationAlarm -> createNotification()
                    is RegisterNavigationAction.NavigateToGoogleLogin -> googleLogin()
                    is RegisterNavigationAction.NavigateToLoginFirst -> navigate(
                        RegisterFragmentDirections.actionRegisterFragmentToSetProfileFragment()
                    )
                    else -> {}
                }
            }
        }
    }


    override fun initAfterBinding() {
    }

    @SuppressLint("HardwareIds")
    private fun createNotification() {
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            viewModel.firebaseToken.value = it
            Log.d("ttt", it.toString())
//            viewModel.deviceId.value = Settings.Secure.getString(requireContext().contentResolver, Settings.Secure.ANDROID_ID)
        }
    }

    private fun googleLogin() {

        /**
         * 1. 오순택, 오순도순 jwt 회원가입 할 때 안드에서 로그인 정보
         * 2. 소셜 로그인도 마찬가지로 구글 로그인 안드에서 하고
         * 3. 그 정보를 서버에 보내는거지
         * 4. googleIdToken <- 그 이후로는 필요한 거
         * 5. 이메일, 닉네임, 프로필 이미지 등..
         * 6. 닉네임이랑 프로필 이미지 설정할 것임
         * 7. googleIdToken
         *
         * 웹 서버, 앱 서버
         * 웹 서버 개발에서는 서버에서 구글 로그인하고 코드받고 그 코드로 다시 토큰이랑 정보 받아옴.
         *
         *
         * 다른 방식일 수 있다.
         *
         * 앱 서버 개발에서는
         * 앱에서 구글 로그인하고 토큰이랑 정보를 받아올 수 있음. 그 정보를 서버에 보내줌.
         *
         *
         *
         * */

        /** 구글 클라우드 플랫폼에서 해당 프로젝트로 들어가면 OAuth 2.0 설정을 한 경우 아래와 같이 나오는데,
         *  이 중 OAuth 2.0 클라이언트 ID 밑의 "Web Client"를 클릭한다.
         *  그러면 오른쪽 위에 클라이언트 ID가 보이는데 그것을 requestIdToken에 넣으면 된다.
         *  R.string.default_web_client_id를 넣어도 작동한다고 하는데 나는 잘 되지 않는다.
         */
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_LOGIN_CLIENT_ID)
            .requestEmail()
            .build()


        val mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        val account = GoogleSignIn.getLastSignedInAccount(requireContext())

        val signInIntent = mGoogleSignInClient.signInIntent

        googleLauncher.launch(signInIntent)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            Log.d(TAG, completedTask.result.idToken.toString())

            val idToken = completedTask.getResult(ApiException::class.java).idToken
            idToken?.let { token ->
                viewModel.oauthLogin(idToken = token)
            }
        } catch (e: ApiException) {
            toastMessage("구글 로그인에 실패 하였습니다.")
        }
    }
}