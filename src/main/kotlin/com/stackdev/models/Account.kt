package com.stackdev.models

data class Account(
    var name:String?=null,
    var accountNumber:String?=null,
    var client:Client?=null,
    var balance:Double?=null
)
