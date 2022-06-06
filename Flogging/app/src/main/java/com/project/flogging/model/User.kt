package com.project.flogging.model

import java.io.Serializable

data class User (
    var userId:Long=0,
    var address:String="",
    var email:String="",
    var point:Long=0,
    var role:String=""
        ):Serializable