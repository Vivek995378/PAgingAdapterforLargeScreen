package com.example.pagingadapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pagingadapter.models.CharacterData
import com.example.pagingadapter.network.ApiInterface
import com.example.pagingadapter.network.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class MainActivityViewModel: ViewModel() {
    lateinit var apiService: ApiInterface

    init {
        apiService = RetrofitInstance.getRetroInstance().create(ApiInterface::class.java)
    }

    fun getListData(): Flow<PagingData<CharacterData>> {
        return Pager (config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = {CharactersPagingSource(apiService)}).flow.cachedIn(viewModelScope)
    }
}