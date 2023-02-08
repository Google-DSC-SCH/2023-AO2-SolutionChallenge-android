package com.ao2.run_eat.ui.setProfile

import androidx.lifecycle.viewModelScope
import com.ao2.run_eat.base.BaseViewModel
import com.ao2.run_eat.ui.setProfile.SetProfileActionHandler
import com.ao2.run_eat.ui.setProfile.SetProfileNavigationAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.*

@HiltViewModel
class SetProfileViewModel @Inject constructor(
) : BaseViewModel(), SetProfileActionHandler {

    private val TAG = "SetProfileViewModel"

    private val _navigationHandler: MutableSharedFlow<SetProfileNavigationAction> = MutableSharedFlow<SetProfileNavigationAction>()
    val navigationHandler: SharedFlow<SetProfileNavigationAction> = _navigationHandler.asSharedFlow()

    var nicknameInputContent = MutableStateFlow<String>("")
    var nicknameEditTextCountEvent = MutableStateFlow<Int>(0)

    var ageInputContent = MutableStateFlow<String>("")
    var ageEditTextCountEvent = MutableStateFlow<Int>(0)

    val setBtnState: MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)
    val setPossibleState: MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)

//    val profileImg: MutableStateFlow<Profile?> = MutableStateFlow(null)

    init {
        baseViewModelScope.launch {
            showLoading()
//            mainRepository.getProfilesRandom()
//                .onSuccess { profile ->
//                    profileImg.emit(profile)
//                }
            dismissLoading()
        }

        baseViewModelScope.launch {
            nicknameInputContent.debounce(0).collectLatest {
                onEditTextCount(it.length)
            }
        }

        baseViewModelScope.launch {
            ageInputContent.debounce(0).collectLatest {
                onAgeEditTextCount(it.length)
            }
        }
    }

    private fun onEditTextCount(count: Int) {
        viewModelScope.launch {
            nicknameEditTextCountEvent.value = count
        }
    }

    private fun onAgeEditTextCount(count: Int) {
        viewModelScope.launch {
            ageEditTextCountEvent.value = count
        }
    }

    override fun onAgeSetClicked(){
        baseViewModelScope.launch {
            _navigationHandler.emit(SetProfileNavigationAction.NavigateToAgeNumberPicker)
        }

    }


    override fun onProfileImageSetClicked() {
        baseViewModelScope.launch {
//            profileImg.value?.let {
//                _navigationHandler.emit(SetProfileNavigationAction.NavigateToSetProfileImage(profile = it))
//            }
        }
    }

    override fun onSelectionDoneClicked() {
        baseViewModelScope.launch {
            showLoading()
//            val idToken = sSharedPreferences.getString("id_token", null)
//            val provider = sSharedPreferences.getString("provider", null)
//            if(inputContent.value == "") {
//                _navigationHandler.emit(SetProfileNavigationAction.NavigateToEmpty)
//            } else {
//                if(idToken != null && provider != null) {
//                    mainRepository.postRegister(
//                        idToken = idToken,
//                        provider = provider,
//                        profile_path = profileImg.value!!.url,
//                        nickname = inputContent.value
//                    ).onSuccess {
//                        editor.putString("access_token", it.access_token)
//                        editor.putString("refresh_token", it.refresh_token)
//                        editor.commit()
//                        val deviceId = sSharedPreferences.getString("device_id", null)
//                        val fcmToken = sSharedPreferences.getString("fcm_token", null)
//                        mainRepository.postNotificationToken(device_id = deviceId!!, token = fcmToken!!)
//                            .onSuccess {
//                                _navigationHandler.emit(SetProfileNavigationAction.NavigateToHome)
//                            }
//                    }
//                }
//            }
            dismissLoading()
        }
    }

}