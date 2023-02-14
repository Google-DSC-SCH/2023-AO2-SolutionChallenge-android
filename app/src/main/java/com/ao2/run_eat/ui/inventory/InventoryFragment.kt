package com.ao2.run_eat.ui.inventory

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ao2.run_eat.R
import com.ao2.run_eat.base.BaseFragment
import com.ao2.run_eat.databinding.FragmentInventoryBinding
import com.ao2.run_eat.ui.inventory.adapter.InventoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class InventoryFragment : BaseFragment<FragmentInventoryBinding, InventoryViewModel>(R.layout.fragment_inventory) {

    private val TAG = "InventoryFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_inventory

    override val viewModel: InventoryViewModel by viewModels()
    private val inventoryAdapter by lazy { InventoryAdapter(viewModel) }

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
        binding.rvInventoryList.adapter = inventoryAdapter

    }

    private fun initAdapter() {
        lifecycleScope.launchWhenStarted {
            viewModel.inventoryList.collectLatest {
                Log.d("ttt 알람 히스토리 데이터 변경", "알람 히스토리 데이터 변경")
                inventoryAdapter.submitData(it)
            }
        }
    }

}
