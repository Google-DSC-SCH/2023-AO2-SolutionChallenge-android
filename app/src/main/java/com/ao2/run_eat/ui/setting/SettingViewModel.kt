package com.ao2.run_eat.ui.setting

import com.ao2.run_eat.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
) : BaseViewModel(), SettingActionHandler {

    private val TAG = "SettingViewModel"

    private val _alarmPushPermitted: MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)
    val alarmPushPermitted: StateFlow<Boolean> = _alarmPushPermitted

    private val _reactionPushPermitted: MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)
    val reactionPushPermitted: StateFlow<Boolean> = _reactionPushPermitted

    private val _notReceivedPushAtNightPermitted: MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)
    val notReceivedPushAtNightPermitted: StateFlow<Boolean> = _notReceivedPushAtNightPermitted

    fun getOptions() {
        baseViewModelScope.launch {
//            mainRepository.getOptions()
//                .onSuccess { options ->
//                    _alarmPushPermitted.value = options.new_option
//                    _reactionPushPermitted.value = options.reaction_option
//                    _notReceivedPushAtNightPermitted.value = options.night_option
//                }
        }
    }

    override fun onNewPushToggled(checked: Boolean) {
        baseViewModelScope.launch {
//            if(checked) mainRepository.postOptionNew()
//            else mainRepository.deleteOptionNew()
        }
    }

    override fun onReactionPushToggled(checked: Boolean) {
        baseViewModelScope.launch {
//            if(checked) mainRepository.postOptionReaction()
//            else mainRepository.deleteOptionReaction()
        }
    }

    override fun onNotReceivedPushAtNight(checked: Boolean) {
        baseViewModelScope.launch {
//            if(checked) mainRepository.postOptionNight()
//            else mainRepository.deleteOptionNight()
        }
    }

}