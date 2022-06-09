package com.project.flogging.model

import java.io.Serializable

data class User (
    var id:Long=0,
    var username:String="",
    var address:String="",
    var email:String="",
    var point:Long=0,
    var role:String="",
    var pointHistories:List<PointHistory>
        ):Serializable