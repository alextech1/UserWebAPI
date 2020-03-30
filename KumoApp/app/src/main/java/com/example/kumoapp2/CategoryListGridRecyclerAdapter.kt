package com.example.kumoapp2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kumoapp2.Model.Category

class CategoryListGridRecyclerAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listOfCategory = listOf<Category>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CategoryListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false))

    }

    override fun getItemCount(): Int = listOfCategory.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val productViewHolder = viewHolder as CategoryListViewHolder
        productViewHolder.bindView(listOfCategory[position])
    }

    fun setProductList(listOfCategory: List<Category>) {
        this.listOfCategory = listOfCategory
        notifyDataSetChanged()
    }
}