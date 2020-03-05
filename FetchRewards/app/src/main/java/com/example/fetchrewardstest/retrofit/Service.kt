package com.example.fetchrewardstest.retrofit

import com.example.fetchrewardstest.model.Item
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface Service {

    @Headers("secret-key: \$2b\$10\$Vr2RAD3mpzFZ6o8bPZNlgOOM0LmFLvN24IoxlELo3arTgNszX7otS")
    @GET("b/5e0f707f56e18149ebbebf5f/2")
    fun getRequest(): Call<List<Item>>
}