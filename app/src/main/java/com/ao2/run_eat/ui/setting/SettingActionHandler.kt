package com.ao2.run_eat.ui.setting


interface SettingActionHandler {
    fun onNewPushToggled(checked: Boolean)
    fun onReactionPushToggled(checked: Boolean)
    fun onNotReceivedPushAtNight(checked: Boolean)
}