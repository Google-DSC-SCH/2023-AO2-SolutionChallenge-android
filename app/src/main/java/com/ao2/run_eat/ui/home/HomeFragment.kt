package com.ao2.run_eat.ui.home

import android.animation.ObjectAnimator
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ao2.run_eat.R
import com.ao2.run_eat.base.BaseFragment
import com.ao2.run_eat.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    private val TAG = "HomeFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_home

    override val viewModel : HomeViewModel by viewModels()

    override fun initStartView() {
        binding.apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
        setupEvent()
    }

    private fun setupEvent() {
        lifecycleScope.launchWhenStarted {
//            viewModel.navigationEvent.collect {
//                when(it) {
//                    is HomeNavigationAction.NavigateToInit -> Unit
//                    is HomeNavigationAction.NavigateToCreate -> navigate(HomeFragmentDirections.actionHomeFragmentToCreateTodoFragment())
//                    is HomeNavigationAction.NavigateToTodoDetail -> navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it.todoIdx))
//                    is HomeNavigationAction.NavigateToPaging -> navigate(HomeFragmentDirections.actionHomeFragmentToPagingFragment())
//                }
//            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.toggleStateEvent.collectLatest {
                toggleFab(it)
            }
        }
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
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

            ObjectAnimator.ofFloat(binding.btToBag, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.btToBag , "translationX", 0f).apply { start() }

            ObjectAnimator.ofFloat(binding.txtToBag, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.txtToBag , "translationX", 0f).apply { start() }


            ObjectAnimator.ofFloat(binding.btTodo, View.ROTATION, 45f, 0f).apply { start() }
        } else { // 플로팅 액션 버튼 열기 - 닫혀있는 플로팅 버튼 꺼내는 애니메이션

            ObjectAnimator.ofFloat(binding.btToShare, "translationY", -360f).apply { start() }
            ObjectAnimator.ofFloat(binding.btToShare, "translationX", +180f).apply { start() }

            ObjectAnimator.ofFloat(binding.txtToShare, "translationY", -360f).apply { start() }
            ObjectAnimator.ofFloat(binding.txtToShare, "translationX", +180f).apply { start() }

            ObjectAnimator.ofFloat(binding.btToBag, "translationY", -120f).apply { start() }
            ObjectAnimator.ofFloat(binding.btToBag, "translationX", +270f).apply { start() }

            ObjectAnimator.ofFloat(binding.txtToBag, "translationY", -120f).apply { start() }
            ObjectAnimator.ofFloat(binding.txtToBag, "translationX", +270f).apply { start() }


            ObjectAnimator.ofFloat(binding.btToPaging, "translationY", -500f).apply { start() }
            ObjectAnimator.ofFloat(binding.txtToPaging, "translationY", -500f).apply { start() }

            ObjectAnimator.ofFloat(binding.btAddTodo, "translationY", -360f).apply { start() }
            ObjectAnimator.ofFloat(binding.btAddTodo, "translationX", -180f).apply { start() }

            ObjectAnimator.ofFloat(binding.txtAddTodo, "translationY", -360f).apply { start() }
            ObjectAnimator.ofFloat(binding.txtAddTodo, "translationX", -180f).apply { start() }

            ObjectAnimator.ofFloat(binding.btDeleteTodo, "translationY", -120f).apply { start() }
            ObjectAnimator.ofFloat(binding.btDeleteTodo, "translationX", -270f).apply { start() }

            ObjectAnimator.ofFloat(binding.txtDeleteTodo, "translationY", -120f).apply { start() }
            ObjectAnimator.ofFloat(binding.txtDeleteTodo, "translationX", -270f).apply { start() }


            ObjectAnimator.ofFloat(binding.btTodo, View.ROTATION, 0f, 45f).apply { start() }
        }
    }


}
