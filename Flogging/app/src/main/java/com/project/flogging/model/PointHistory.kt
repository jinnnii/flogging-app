package com.project.flogging.model

import java.util.*

data class PointHistory(
    var id:Long,
    var detail:String,
    var point:Long,
    var user:User,
    var regdate:Date
)
