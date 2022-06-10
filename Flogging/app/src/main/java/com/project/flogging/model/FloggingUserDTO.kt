package com.project.flogging.model

import java.io.Serializable

data class FloggingUserDTO(
    var distance: Double,
    var endTime: Long,
    var startTime: Long,
    var time: Long,
):Serializable
