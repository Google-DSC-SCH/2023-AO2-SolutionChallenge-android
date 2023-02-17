package com.ao2.run_eat.ui

import com.ao2.run_eat.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel(), MainActionHandler {

    private val TAG = "MainViewModel"

    var toggleStateEvent: MutableStateFlow<Boolean> = MutableStateFlow(false)


    private val _navigationHandler: MutableSharedFlow<MainNavigationAction> = MutableSharedFlow<MainNavigationAction>()
    val navigationHandler: SharedFlow<MainNavigationAction> = _navigationHandler.asSharedFlow()

    private fun whenToggleState() {
        if(toggleStateEvent.value) {
            toggleStateEvent.value = !toggleStateEvent.value
        }
    }

    override fun onToggleFab() {
        toggleStateEvent.value = !toggleStateEvent.value
    }

    override fun onToggleRunningClicked() {
        baseViewModelScope.launch {
            _navigationHandler.emit(MainNavigationAction.NavigateToRunning)
            whenToggleState()
        }
    }

    override fun onToggleInventorClicked() {
        baseViewModelScope.launch {
            _navigationHandler.emit(MainNavigationAction.NavigateToInventor)
            whenToggleState()
        }
    }

    override fun onToggleSettingClicked() {
        baseViewModelScope.launch {
            _navigationHandler.emit(MainNavigationAction.NavigateToSetting)
            whenToggleState()
        }
    }

    fun onToggleHomeClicked() {
        baseViewModelScope.launch {
            _navigationHandler.emit(MainNavigationAction.NavigateToHome)
            whenToggleState()

        }
    }
}