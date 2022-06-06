package com.project.flogging

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SpringApplication:Application() {
    var networkService: INetworkService
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("http://192.168.0.7:8084/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    init {
        networkService = retrofit.create(INetworkService::class.java)
    }
}