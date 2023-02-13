package com.ao2.run_eat.ui.home

import com.ao2.run_eat.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : BaseViewModel(), HomeActionHandler {
    var toggleStateEvent: MutableStateFlow<Boolean> = MutableStateFlow(false)

    private val TAG = "HomeViewModel"

    private fun whenToggleState() {
        if(toggleStateEvent.value) {
            toggleStateEvent.value = !toggleStateEvent.value
        }
    }

    override fun onToggleFab() {
        toggleStateEvent.value = !toggleStateEvent.value
    }

}