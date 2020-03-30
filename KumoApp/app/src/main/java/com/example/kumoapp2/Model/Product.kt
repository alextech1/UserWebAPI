package com.example.kumoapp2.Model

data class Product (var id: Int,
                    var name: String,
                    var photo: String,
                    var price: Double) {
    //constructor() : this(0, "", 0, 0.00)
    override fun toString(): String =
            "Product{" +
            "id=" + id +
            "name=" + name +
            "photo=" + photo +
            "price=" + price +
            '}'
}