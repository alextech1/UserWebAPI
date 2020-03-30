package com.example.kumoapp2.Model

class Cart (var id: Int,
            var product: Product,
            var quantity: Int
            ) {
    //constructor() : this(0, "", 0, 0.00)
    override fun toString(): String =
        "Cart{" +
                "id=" + id +
                "product=" + product +
                "quantity=" + quantity +
                '}'
}