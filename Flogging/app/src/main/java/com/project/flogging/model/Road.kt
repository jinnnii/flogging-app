package com.project.flogging.model

import java.io.Serializable

data class Road(
    var id: Long?,
    var latitude:Double=0.0,
    var longitude:Double=0.0,
    var time:Long=0,
    var flogingUser: FlogingUser?,
        ):Serializable