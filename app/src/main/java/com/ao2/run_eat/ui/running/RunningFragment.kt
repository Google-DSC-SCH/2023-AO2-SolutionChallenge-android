package com.ao2.run_eat.ui.running

import androidx.fragment.app.viewModels
import com.ao2.run_eat.R
import com.ao2.run_eat.base.BaseFragment
import com.ao2.run_eat.databinding.FragmentRunningBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RunningFragment : BaseFragment<FragmentRunningBinding, RunningViewModel>(R.layout.fragment_running) {

    private val TAG = "RunningFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_running

    override val viewModel : RunningViewModel by viewModels()

    override fun initStartView() {
        binding.apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }


}