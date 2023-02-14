package com.ao2.run_eat.ui.inventory.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ao2.domain.model.Inventory
import com.ao2.run_eat.R
import com.ao2.run_eat.databinding.HolderInventoryBinding
import com.ao2.run_eat.ui.inventory.InventoryViewModel
import java.util.*


class InventoryAdapter(
    private val eventListener: InventoryViewModel
) : PagingDataAdapter<Inventory, InventoryAdapter.ViewHolder>(
    AlarmRoomHistoryMessageItemDiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDataBinding: HolderInventoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.holder_inventory,
            parent,
            false
        )
        viewDataBinding.eventListener = eventListener
        return ViewHolder(viewDataBinding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(item = it)
        }
    }

    class ViewHolder(private val binding: HolderInventoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Inventory) {

            binding.apply {
                holder = item
                executePendingBindings()
            }
        }
    }

}

internal object AlarmRoomHistoryMessageItemDiffCallback : DiffUtil.ItemCallback<Inventory>() {
    override fun areItemsTheSame(oldItem: Inventory, newItem: Inventory) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Inventory, newItem: Inventory) =
        oldItem == newItem
}
