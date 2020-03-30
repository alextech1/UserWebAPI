package com.example.kumoapp2

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kumoapp2.Model.Product
import com.bumptech.glide.Glide
import com.example.kumoapp2.Model.Category
import kotlinx.android.synthetic.main.product_row.view.*

class CategoryListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(categoryModel: Category) {
        itemView.name.text = categoryModel.name

        Glide.with(itemView.context).load(categoryModel.photo!!).into(itemView.photo)
    }
}