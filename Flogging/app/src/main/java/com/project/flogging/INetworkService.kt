package com.project.flogging

import com.project.flogging.model.*
import retrofit2.Call
import retrofit2.http.*

interface INetworkService {
//    유저네임으로 유저객체 하나 받아오기
    @GET("user/getUser/{username}")
    fun findUser(@Path("username") username: String):Call<User>

//    플로깅 유저 저장, 플로깅 유저랑 로드리스트는 따로 분리해서 보내주세요. 리턴값은 success 입니다.
    @POST("flogingUser/insert")
    fun insertFlogingUser(
    @Query("distance")distance:Double,
    @Query("time")time:Long,
    @Query("startTime")startTime:Long,
    @Query("endTime")endTime:Long,
    @Body roads:List<Road>,@Query("username")username:String):Call<MessageModel>
    
//    포인트 적립+내역 볼 수 있음
    @POST("user/point")
    fun point(
        @Query("username") username:String,
        @Query("pointHistory") pointHistory: PointHistory
    ):Call<User>

//    피드백 저장, 리턴값은 success, user정보는 flogingUser의 user에서 빼서 쓰면되니까 feedback의 user는 지움
    @POST("flogingUser/feedback")
    fun insertFeedback(
        @Query("username") username:String,
        @Body feedback: Feedback
    ):Call<MessageModel>

    @POST("flogingUser/test")
    fun test(
        @Body flogingUser:FlogingUser
    ):Call<MessageModel>
}