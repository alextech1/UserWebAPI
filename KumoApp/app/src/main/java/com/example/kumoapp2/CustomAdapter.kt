package com.example.kumoapp2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.kumoapp2.Model.Product

class CustomAdapter(private val context: Context, private val productList: List<Product>, private val flags: IntArray):
    BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return productList.size
    }

    override fun getItem(i: Int): Any {
        return productList[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        //view = inflter.inflate(R.layout.activity_listview, null)
        val rowView = inflater.inflate(R.layout.activity_listview, null)
        val country = rowView.findViewById(R.id.textView) as TextView
        val icon = rowView.findViewById(R.id.icon) as ImageView
        country.text = productList[i].name
        icon.setImageResource(flags[i])
        return rowView
    }
}