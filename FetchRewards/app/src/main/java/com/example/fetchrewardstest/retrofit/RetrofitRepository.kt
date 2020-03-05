package com.example.fetchrewardstest.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fetchrewardstest.FetchRewardsApplication
import com.example.fetchrewardstest.dagger.AppComponent
import com.example.fetchrewardstest.model.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitRepository {

    companion object {
        private val TAG = RetrofitRepository::class.qualifiedName
    }

    private var appComponent: AppComponent = FetchRewardsApplication.appComponent
    var mItemMutableList: MutableLiveData<List<Item>> = MutableLiveData()

    @Inject
    lateinit var retrofit: Retrofit

    init {
        appComponent.inject(this)
    }

    fun getItemList(): LiveData<List<Item>> {
        val service = retrofit.create(Service::class.java)
        val itemList: Call<List<Item>> = service.getRequest();
        itemList.enqueue(object : Callback<List<Item>> {
            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                Log.e(TAG, "Call failed.", t);
            }

            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                val itemListResponse = response.body()
                mItemMutableList.value = itemListResponse
            }
        })

        return mItemMutableList
    }
}