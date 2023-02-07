package com.ao2.run_eat.ui.register

import android.util.Log
import com.ao2.run_eat.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
) : BaseViewModel(), RegisterActionHandler {

    private val TAG = "RegisterViewModel"

    private val _navigationHandler: MutableSharedFlow<RegisterNavigationAction> = MutableSharedFlow<RegisterNavigationAction>()
    val navigationHandler: SharedFlow<RegisterNavigationAction> = _navigationHandler.asSharedFlow()
    val notificationAgreed: MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)
    val firebaseToken: MutableStateFlow<String> = MutableStateFlow("")

    init {
    }

    fun sendNotification() {
        baseViewModelScope.launch {
            showLoading()
            notificationAgreed.value = true
//            mainRepository.postNotificationExperience(token = firebaseToken.value, content = messageText.value)
            dismissLoading()
        }
    }

    fun oauthLogin(idToken: String) {
        baseViewModelScope.launch {
            showLoading()
//            mainRepository.getTokenValidation(idToken = idToken, provider = provider)
//                .onSuccess {
//                    editor.putString("id_token", idToken)
//                    editor.putString("provider", provider)
//                    editor.putString("fcm_token", firebaseToken.value)
//                    editor.putString("device_id", deviceId.value)
//
//                    if(!it.is_registered) {
//                        editor.commit()
                        _navigationHandler.emit(RegisterNavigationAction.NavigateToLoginFirst)
//                    } else {
//                        mainRepository.postLogin(idToken = idToken, provider = provider)
//                            .onSuccess { response ->
//                                editor.putString("access_token", response.access_token)
//                                editor.putString("refresh_token", response.refresh_token)
//                                editor.commit()
//
//                                mainRepository.postNotificationToken(token = firebaseToken.value, device_id = deviceId.value)
//                                    .onSuccess {
//                                        _navigationHandler.emit(RegisterNavigationAction.NavigateToLoginAlready)
//                                    }
//                            }
//                    }
//                }
            dismissLoading()
        }
    }

    override fun onSendTestPushAlarmClicked() {
        baseViewModelScope.launch {
            Log.d("ttt", "1")

            if (!notificationAgreed.value) {
                Log.d("ttt", "3")

                _navigationHandler.emit(RegisterNavigationAction.NavigateToPushSetting)
            } else {
                Log.d("ttt", "4")

                _navigationHandler.emit(RegisterNavigationAction.NavigateToNotificationAlarm)
            }
        }
    }

    override fun onGoogleLoginClicked() {
        baseViewModelScope.launch {
            _navigationHandler.emit(RegisterNavigationAction.NavigateToGoogleLogin)
        }

    }

}