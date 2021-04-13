package com.example.kumoapp2.Model

data class User(
    //val Id: String?,
    val FirstName:String,
    val LastName:String,
    val UserName:String,
    val Password:String,
    val Email:String,
    val Address:String,
    val Role:Int
    //val OrderStatus:Int,
    //val Token:String,
    //val uid:String?
)


//old class
/*class User {
    var FirstName:String? = null
    var LastName:String? = null
    var UserName:String? = null
    var Password:String? = null
    var Email:String? = null
    var Role:Int? = 0
    var Address:String? = null
    var OrderStatus:Int? = 0
    var Token:String? = null
    var uid:String? = null
    var emailFb:String? = null

    constructor()

    constructor(uid:String, email:String)
    {
        this.uid = uid
        this.emailFb = email
    }

    constructor(FirstName:String?, LastName:String?,UserName:String, Password:String,
                Email:String, Address:String, Role:Int)
    {
        this.FirstName = FirstName
        this.LastName = LastName
        this.UserName = UserName
        this.Password = Password
        this.Email = Email
        this.Address = Address
        this.Role = Role
        this.OrderStatus = 0
    }

    /*constructor(UserName: String, Password: String)
    {
        this.UserName = UserName
        this.Password = Password
    }*/
}*/