package com.example.fetchrewardstest

import android.app.Application
import com.example.fetchrewardstest.dagger.DaggerAppComponent
import com.example.fetchrewardstest.dagger.AppComponent
import com.example.fetchrewardstest.dagger.AppModule
import com.example.fetchrewardstest.dagger.RetrofitModule

class FetchRewardsApplication : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().retrofitModule(RetrofitModule()).build();
    }
}