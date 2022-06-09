package com.project.flogging.model

data class Feedback(
    //user정보는 flogingUser의 user에서 빼서 쓰면되니까 feedback의 user는 지움
    var id:Long,
    var feedbackLevel:String,
    var detail:String,
    var flogingUser: FloggingUser?
)
