package com.example.eketan.model

import com.rizkiprats.programming.myuas.model.DataModel
import com.rizkiprats.programming.myuas.model.RekapModel

data class LoginResponse(
    val response: Boolean,
    val payload: LoginRequest,
    val payload_2: ArrayList<DataModel>
    )

