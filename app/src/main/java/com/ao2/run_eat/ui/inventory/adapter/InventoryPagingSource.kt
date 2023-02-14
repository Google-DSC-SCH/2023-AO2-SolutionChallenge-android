//package com.ao2.run_eat.ui.inventory.adapter
//
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.paging.Pager
//import androidx.paging.PagingConfig
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.ao2.domain.model.Inventory
//import com.ao2.domain.repository.MainRepository
//import com.ao2.run_eat.ui.inventory.InventoryViewModel
//import kotlinx.coroutines.flow.StateFlow
//
//fun createInventoryPager(
//    mainRepository: MainRepository,
//    groupId: StateFlow<Int>,
//    sort: StateFlow<String>,
//    viewModel: InventoryViewModel
//): Pager<Int, Inventory> = Pager(
//    config = PagingConfig(pageSize = 10, initialLoadSize = 10, enablePlaceholders = true),
//    initialKey = 0,
//    pagingSourceFactory = {
//        AlarmRoomHistoryMessagePagingSource(
//            mainRepository = mainRepository,
//            groupId = groupId,
//            sort = sort,
//            viewModel = viewModel
//        )
//    }
//)
//
//class AlarmRoomHistoryMessagePagingSource(
//    private val mainRepository: MainRepository,
//    private val groupId: StateFlow<Int>,
//    private val sort: StateFlow<String>,
//    private val viewModel: InventoryViewModel
//
//) : PagingSource<Int, Inventory>() {
//
//    override fun getRefreshKey(state: PagingState<Int, Inventory>): Int? = null
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Inventory> {
//        val pageIndex = params.key ?: 0
//
//        val result = mainRepository.getNotification(
//            page = pageIndex,
//            size = params.loadSize,
//            sort = sort.value,
//            group_id = groupId.value,
//
//            ).onSuccess {
//
//        }
//
//        return result.fold(
//            onSuccess = { contents ->
//                LoadResult.Page(
//                    data = contents.notifications.content,
//                    prevKey = null,
//                    nextKey = if (!contents.notifications.last) pageIndex + 1 else null
//                )
//            },
//            onError = { e -> LoadResult.Error(e) }
//        )
//    }
//}