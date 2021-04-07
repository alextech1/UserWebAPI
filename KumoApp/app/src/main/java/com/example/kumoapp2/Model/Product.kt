package com.example.kumoapp2.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Description")
    val description: String,
    @SerializedName("Price")
    val price: Double,
    @SerializedName("Photo")
    val photo: String,
    @SerializedName("Quantity")
    val quantity: Int
) {
    //constructor() : this(0, "", 0, 0.00)
    override fun toString(): String =
        "Product{" +
                "id=" + id +
                " name=" + name +
                " description=" + description +
                " price=" + price +
                " photo=" + photo +
                " quantity=" + quantity +
                '}'
}