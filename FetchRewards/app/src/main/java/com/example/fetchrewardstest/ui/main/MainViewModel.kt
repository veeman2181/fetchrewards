package com.example.fetchrewardstest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.fetchrewardstest.FetchRewardsApplication
import com.example.fetchrewardstest.dagger.AppComponent
import com.example.fetchrewardstest.model.Item
import com.example.fetchrewardstest.retrofit.RetrofitRepository
import javax.inject.Inject

class MainViewModel : ViewModel() {
    private var mAppComponent: AppComponent = FetchRewardsApplication.appComponent
    private var mItemLiveData: LiveData<List<Item>> = MutableLiveData()

    @Inject
    lateinit var mRetrofitRepository: RetrofitRepository

    init {
        mAppComponent.inject(this)
    }

    private fun getItemList(): LiveData<List<Item>> {
        mItemLiveData = mRetrofitRepository.getItemList()
        return mItemLiveData
    }

    fun getFilteredList(): LiveData<List<Item>> {
        // Filter out all null and empty names and then arrange by ListId first, then name.
        return Transformations.map(getItemList()) { it ->
            it.filter { item -> !item.name.isNullOrEmpty() }.sortedWith(
                compareBy({ it.listId }, { it.name?.split(" ")?.getOrNull(1)?.toInt() })
            )
        }
    }
}
