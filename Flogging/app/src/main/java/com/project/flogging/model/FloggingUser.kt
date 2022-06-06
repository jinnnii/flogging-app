package com.project.flogging.model

import java.io.Serializable
import kotlin.collections.ArrayList

data class FloggingUser(
    var id: String? ="",
    var distance:Double=0.0,
    var endTime:Long=0,
    var startTime:Long=0,
    var time:Long=0,
    var user:User = User(),
    var road:ArrayList<Road> = arrayListOf()
        ):Serializable