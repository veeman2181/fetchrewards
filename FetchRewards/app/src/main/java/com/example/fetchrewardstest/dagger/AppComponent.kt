package com.example.fetchrewardstest.dagger

import com.example.fetchrewardstest.retrofit.RetrofitRepository
import com.example.fetchrewardstest.ui.main.MainFragment
import com.example.fetchrewardstest.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, AppModule::class])
interface AppComponent {
    fun inject(retrofitRepository: RetrofitRepository)
    fun inject(mainFragment: MainFragment)
    fun inject(mainViewModel: MainViewModel)
}