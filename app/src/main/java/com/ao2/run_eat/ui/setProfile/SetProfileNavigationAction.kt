package com.ao2.run_eat.ui.setProfile



sealed class SetProfileNavigationAction {
//    class NavigateToSetProfileImage(val profile: Profile): SetProfileNavigationAction()
    object NavigateToHome: SetProfileNavigationAction()
    object NavigateToEmpty: SetProfileNavigationAction()
    object NavigateToAgeNumberPicker: SetProfileNavigationAction()

}