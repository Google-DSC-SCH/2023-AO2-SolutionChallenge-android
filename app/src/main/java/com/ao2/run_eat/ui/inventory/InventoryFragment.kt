package com.ao2.run_eat.ui.inventory

import androidx.fragment.app.viewModels
import com.ao2.run_eat.R
import com.ao2.run_eat.base.BaseFragment
import com.ao2.run_eat.databinding.FragmentHomeBinding
import com.ao2.run_eat.databinding.FragmentInventoryBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InventoryFragment : BaseFragment<FragmentInventoryBinding, InventoryViewModel>(R.layout.fragment_inventory) {

    private val TAG = "HomeFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_inventory

    override val viewModel: InventoryViewModel by viewModels()

    override fun initStartView() {
        binding.apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
        setupEvent()
    }

    private fun setupEvent() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

}
