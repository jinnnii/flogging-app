package com.project.flogging.model

import java.io.Serializable
import kotlin.collections.ArrayList

data class FlogingUser(
    var id: Long?,
    var user:User?,
    var distance: Double = 0.0,
    var endTime: Long = 0,
    var startTime: Long = 0,
    var time: Long = 0,
    var road: ArrayList<Road>?
):Serializable