package com.example.kumoapp2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kumoapp2.Model.Category

class CategoryListGridRecyclerAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listOfCategory = listOf<Category>()
    lateinit var  mOnItemClickListener: (category: Category, pos: Int)-> Unit

    fun setOnItemClickListener(callback: (category: Category, pos: Int)-> Unit){
        mOnItemClickListener = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CategoryListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false))
            .listen { pos, _ ->
                if(::mOnItemClickListener.isInitialized){
                    mOnItemClickListener(listOfCategory[pos], pos)
                }
            }
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position:Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
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