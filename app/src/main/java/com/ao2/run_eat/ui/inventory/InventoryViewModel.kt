package com.ao2.run_eat.ui.inventory

import androidx.paging.PagingData
import com.ao2.domain.model.Inventory
import com.ao2.domain.repository.MainRepository
import com.ao2.run_eat.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
//    private val mainRepository: MainRepository
) : BaseViewModel(), InventoryActionHandler {

    private val TAG = "InventoryViewModel"

    private val _navigationHandler: MutableSharedFlow<InventoryNavigationAction> = MutableSharedFlow<InventoryNavigationAction>()
    val navigationHandler: SharedFlow<InventoryNavigationAction> = _navigationHandler.asSharedFlow()

    var inventoryList: Flow<PagingData<Inventory>> = emptyFlow()
    var groupId = MutableStateFlow<Int>(0)
    var sort = MutableStateFlow<String>("")


    fun getInventory() {
//        inventoryList = createInventoryPager(
//            mainRepository = mainRepository,
//            groupId = groupId,
//            sort = sort,
//            viewModel = this
//        ).flow.cachedIn(baseViewModelScope)
    }

}