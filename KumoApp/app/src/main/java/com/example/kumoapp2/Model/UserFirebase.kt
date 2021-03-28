package com.example.kumoapp2.Model

class UserFirebase {
    var uid:String?=null
    var email:String?=null
    var acceptList:HashMap<String, User>?=null

    constructor()

    constructor(uid:String, email:String)
    {
        this.uid = uid
        this.email = email
        //acceptList = HashMap()
    }
}