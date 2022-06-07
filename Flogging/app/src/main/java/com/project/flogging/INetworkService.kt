package com.project.flogging

import com.project.flogging.model.FloggingUser
import com.project.flogging.model.MessageModel
import com.project.flogging.model.User
import retrofit2.Call
import retrofit2.http.*

interface INetworkService {
//    유저네임으로 유저객체 하나 받아오기
    @GET("user")
    fun doTest(@Path("user") user: User):Call<User>
}