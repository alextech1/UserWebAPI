package com.example.kumoapp2.Model

class User {
    var UserName:String? = null
    var Password:String? = null
    var Salt:String? = null
    var Email:String? = null
    var Address:String? = null
    var FirstName:String? = null
    var LastName:String? = null


    constructor(){}

    constructor(UserName:String, Password:String, Email:String, Address:String, FirstName:String?, LastName:String?)
    {
        this.UserName = UserName
        this.Password = Password
        this.Salt = ""
        this.Email = Email
        this.Address = Address
        this.FirstName = FirstName
        this.LastName = LastName
    }

    constructor(UserName: String, Password: String)
    {
        this.UserName = UserName
        this.Password = Password
        this.Salt = ""
    }
}