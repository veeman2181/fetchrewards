package com.example.fetchrewardstest.dagger

import android.content.Context
import com.example.fetchrewardstest.FetchRewardsApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (private val app: FetchRewardsApplication){

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = app

}