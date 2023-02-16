package com.ao2.run_eat.ui

import android.animation.ObjectAnimator
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ao2.run_eat.NavigationGraphDirections
import com.ao2.run_eat.R
import com.ao2.run_eat.base.BaseActivity
import com.ao2.run_eat.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: MainViewModel by viewModels()

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    var waitTime = 0L

    override fun initStartView() {
        binding.apply {
            this.vm = viewModel
        }

        initNavController()

        /** DynamicLink 수신확인 */
        initDynamicLink()

    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

    private fun initNavController() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> showBottomNav()
                R.id.RunningFragment -> showBottomNav()
                R.id.InventorFragment -> showBottomNav()
                R.id.SettingFragment -> showBottomNav()

                else -> hideBottomNav()
            }
        }

        setupEvent()


//        binding.btTodo.setupWithNavController(navController)
//
//        // 중복터치 막기!!
//        binding.btTodo.setOnItemReselectedListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.homeFragment -> {}
//                R.id.alarmRoomTabFragment -> {}
//                R.id.myPageFragment -> {}
//            }
//        }
    }

    private fun setupEvent() {
        lifecycleScope.launchWhenStarted {
            viewModel.navigationHandler.collect {
                Log.d("ttt", "1")

                when (it) {
//                    is HomeNavigationAction.NavigateToInit -> Unit
                    is MainNavigationAction.NavigateToRunning -> navController.navigate(R.id.RunningFragment)
                    is MainNavigationAction.NavigateToInventor -> navController.navigate(R.id.InventorFragment)
                    is MainNavigationAction.NavigateToSetting -> navController.navigate(R.id.SettingFragment)
//                    is HomeNavigationAction.NavigateToTodoDetail -> navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it.todoIdx))
//                    is HomeNavigationAction.NavigateToPaging -> navigate(HomeFragmentDirections.actionHomeFragmentToPagingFragment())
                    else -> {}
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.toggleStateEvent.collectLatest {
                toggleFab(it)
            }
        }
    }

    private fun showBottomNav() {
        binding.btTodo.visibility = View.VISIBLE
        binding.btAddTodo.visibility = View.VISIBLE
        binding.btDeleteTodo.visibility = View.VISIBLE
        binding.btToInventory.visibility = View.VISIBLE
        binding.btToPaging.visibility = View.VISIBLE
        binding.btToShare.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.btTodo.visibility = View.GONE
        binding.btAddTodo.visibility = View.GONE
        binding.btDeleteTodo.visibility = View.GONE
        binding.btToInventory.visibility = View.GONE
        binding.btToPaging.visibility = View.GONE
        binding.btToShare.visibility = View.GONE
    }

    private fun toggleFab(state: Boolean) {
        // 플로팅 액션 버튼 닫기 - 열려있는 플로팅 버튼 집어넣는 애니메이션
        if (!state) {
            ObjectAnimator.ofFloat(binding.btAddTodo, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.btAddTodo, "translationX", 0f).apply { start() }

            ObjectAnimator.ofFloat(binding.txtAddTodo, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.txtAddTodo, "translationX", 0f).apply { start() }

            ObjectAnimator.ofFloat(binding.btToPaging, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.btToPaging, "translationX", 0f).apply { start() }

            ObjectAnimator.ofFloat(binding.txtToPaging, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.txtToPaging, "translationX", 0f).apply { start() }

            ObjectAnimator.ofFloat(binding.btDeleteTodo, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.btDeleteTodo, "translationX", 0f).apply { start() }

            ObjectAnimator.ofFloat(binding.txtDeleteTodo, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.txtDeleteTodo, "translationX", 0f).apply { start() }

            ObjectAnimator.ofFloat(binding.btToShare, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.btToShare, "translationX", 0f).apply { start() }

            ObjectAnimator.ofFloat(binding.txtToShare, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.txtToShare, "translationX", 0f).apply { start() }

            ObjectAnimator.ofFloat(binding.btToInventory, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.btToInventory, "translationX", 0f).apply { start() }

            ObjectAnimator.ofFloat(binding.txtToInventory, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.txtToInventory, "translationX", 0f).apply { start() }

            ObjectAnimator.ofFloat(binding.btTodo, View.ROTATION, 45f, 0f).apply { start() }

        } else { // 플로팅 액션 버튼 열기 - 닫혀있는 플로팅 버튼 꺼내는 애니메이션
            ObjectAnimator.ofFloat(binding.btToShare, "translationY", -360f).apply { start() }
            ObjectAnimator.ofFloat(binding.btToShare, "translationX", +270f).apply { start() }

            ObjectAnimator.ofFloat(binding.txtToShare, "translationY", -360f).apply { start() }
            ObjectAnimator.ofFloat(binding.txtToShare, "translationX", +270f).apply { start() }

            ObjectAnimator.ofFloat(binding.btToInventory, "translationY", -120f).apply { start() }
            ObjectAnimator.ofFloat(binding.btToInventory, "translationX", +270f).apply { start() }

            ObjectAnimator.ofFloat(binding.txtToInventory, "translationY", -120f).apply { start() }
            ObjectAnimator.ofFloat(binding.txtToInventory, "translationX", +270f).apply { start() }

            ObjectAnimator.ofFloat(binding.btToPaging, "translationY", -240f).apply { start() }
            ObjectAnimator.ofFloat(binding.txtToPaging, "translationY", -240f).apply { start() }

            ObjectAnimator.ofFloat(binding.btAddTodo, "translationY", -360f).apply { start() }
            ObjectAnimator.ofFloat(binding.btAddTodo, "translationX", -270f).apply { start() }

            ObjectAnimator.ofFloat(binding.txtAddTodo, "translationY", -360f).apply { start() }
            ObjectAnimator.ofFloat(binding.txtAddTodo, "translationX", -270f).apply { start() }

            ObjectAnimator.ofFloat(binding.btDeleteTodo, "translationY", -120f).apply { start() }
            ObjectAnimator.ofFloat(binding.btDeleteTodo, "translationX", -270f).apply { start() }

            ObjectAnimator.ofFloat(binding.txtDeleteTodo, "translationY", -120f).apply { start() }
            ObjectAnimator.ofFloat(binding.txtDeleteTodo, "translationX", -270f).apply { start() }

            ObjectAnimator.ofFloat(binding.btTodo, View.ROTATION, 0f, 45f).apply { start() }
        }
    }

    override fun onBackPressed() {
        try {
            if (onBackPressedDispatcher.hasEnabledCallbacks()) {
                super.onBackPressed()
            } else {
                when (navController.currentDestination?.id) {
                    R.id.homeFragment -> {
                        if (System.currentTimeMillis() - waitTime >= 1500) {
                            waitTime = System.currentTimeMillis()
                            Toast.makeText(
                                this,
                                getString(R.string.onBackPressed_Message),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            finishAffinity() // 액티비티 종료
                        }
                    }
                    null -> super.onBackPressed()
                    else -> navController.navigate(NavigationGraphDirections.actionMainFragment())
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    /** DynamicLink */
    private fun initDynamicLink() {
        val dynamicLinkData = intent.extras
        if (dynamicLinkData != null) {
            var dataStr = "DynamicLink 수신받은 값\n"
            for (key in dynamicLinkData.keySet()) {
                dataStr += "key: $key / value: ${dynamicLinkData.getString(key)}\n"
            }

        }
    }
}
