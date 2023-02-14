package com.ao2.run_eat.ui.home

import com.ao2.run_eat.base.BaseViewModel
import com.ao2.run_eat.ui.register.RegisterNavigationAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : BaseViewModel() {

    private val TAG = "HomeViewModel"

}