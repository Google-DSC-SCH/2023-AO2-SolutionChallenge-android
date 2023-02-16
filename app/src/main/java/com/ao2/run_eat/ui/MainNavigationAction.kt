package com.ao2.run_eat.ui

sealed class MainNavigationAction {
    object NavigateToRunning: MainNavigationAction()
    object NavigateToInventor: MainNavigationAction()
    object NavigateToSetting: MainNavigationAction()

}