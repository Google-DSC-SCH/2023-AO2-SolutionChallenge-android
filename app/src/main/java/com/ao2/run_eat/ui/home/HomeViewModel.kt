package com.ao2.run_eat.ui.home

import com.ao2.run_eat.base.BaseViewModel
import com.ao2.run_eat.ui.register.RegisterNavigationAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : BaseViewModel(), HomeActionHandler {

    private val TAG = "HomeViewModel"

    var toggleStateEvent: MutableStateFlow<Boolean> = MutableStateFlow(false)


    private val _navigationHandler: MutableSharedFlow<HomeNavigationAction> = MutableSharedFlow<HomeNavigationAction>()
    val navigationHandler: SharedFlow<HomeNavigationAction> = _navigationHandler.asSharedFlow()

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
            _navigationHandler.emit(HomeNavigationAction.NavigateToRunning)
            whenToggleState()
        }
    }

}