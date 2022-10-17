package com.stackdev.models

import java.time.LocalDateTime

data class Client(
    var firstName:String?=null,
    var lastName:String?=null,
    var idNumber:String?=null,
    var age: Int?=null,
    var created:LocalDateTime?= LocalDateTime.now()
)
