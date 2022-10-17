package com.stackdev.models

data class Bank(
    var name:String?=null,
    var bankCode:String?=null,
    var branch: Branch,
    var accounts:List<Account>?=null,
)

data class Branch(
    var name:String?=null,
    var code:String?=null
)
