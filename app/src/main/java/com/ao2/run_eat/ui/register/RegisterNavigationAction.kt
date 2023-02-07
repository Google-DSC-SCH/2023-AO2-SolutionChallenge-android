package com.ao2.run_eat.ui.register

sealed class RegisterNavigationAction {
    object NavigateToGoogleLogin : RegisterNavigationAction()
}