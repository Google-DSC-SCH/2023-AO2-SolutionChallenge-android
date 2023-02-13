package com.ao2.run_eat.ui.home

import com.ao2.run_eat.ui.setProfile.SetProfileNavigationAction


sealed class HomeNavigationAction {
    object NavigateToRunning: HomeNavigationAction()

}