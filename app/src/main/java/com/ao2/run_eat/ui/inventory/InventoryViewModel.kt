package com.ao2.run_eat.ui.inventory

import com.ao2.run_eat.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor() : BaseViewModel() {

    private val TAG = "InventoryViewModel"

    private val _navigationHandler: MutableSharedFlow<InventoryNavigationAction> = MutableSharedFlow<InventoryNavigationAction>()
    val navigationHandler: SharedFlow<InventoryNavigationAction> = _navigationHandler.asSharedFlow()

}