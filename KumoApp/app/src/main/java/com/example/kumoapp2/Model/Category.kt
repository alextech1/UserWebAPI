package com.example.kumoapp2.Model

data class Category (var id: Int,
                     var name: String,
                     var photo: Int?,
                     var price: Double) {
    //constructor() : this(0, "", 0, 0.00)
    override fun toString(): String =
        "Category{" +
                "id=" + id +
                "name=" + name +
                "photo=" + photo +
                "price=" + price +
                '}'
}