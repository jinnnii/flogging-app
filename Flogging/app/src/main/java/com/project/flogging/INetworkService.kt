package com.project.flogging

import com.project.flogging.model.MessageModel
import retrofit2.Call
import retrofit2.http.*

interface INetworkService {
    @GET("user/test")
    fun doTest():Call<MessageModel>
}