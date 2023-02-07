package com.ao2.run_eat.ui.register

sealed class RegisterNavigationAction {
    object NavigateToPushSetting: RegisterNavigationAction()
    object NavigateToNotificationAlarm : RegisterNavigationAction()
    object NavigateToGoogleLogin : RegisterNavigationAction()
    object NavigateToLoginFirst : RegisterNavigationAction()
    object NavigateToLoginAlready : RegisterNavigationAction()
}