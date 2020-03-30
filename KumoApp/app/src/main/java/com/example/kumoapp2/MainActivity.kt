package com.example.kumoapp2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kumoapp2.Interface.IMyAPI
import com.example.kumoapp2.Interface.RetrofitClient
import com.example.kumoapp2.Model.Category
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var mAuthListener : FirebaseAuth.AuthStateListener? = null

    lateinit var myApi: IMyAPI
    lateinit var txt_user_name:TextView
    lateinit var txt_email_address:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        myApi = RetrofitClient.getInstance().create(IMyAPI::class.java)

        Log.d("__MAIN__", "OnCreate")
    }

    private fun initView() {
        recycler_view.layoutManager = GridLayoutManager(this, 2)
        recycler_view.addItemDecoration(GridItemDecoration(10, 2))

        val categoryListAdapter = CategoryListGridRecyclerAdapter()
        recycler_view.adapter = categoryListAdapter
        categoryListAdapter.setProductList(generateDummyData())

        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)
        navView.bringToFront()
    }

    private fun generateDummyData(): List<Category> {
        val listOfCategory = mutableListOf<Category>()

        var categoryModel = Category(1, "Products", R.drawable.kumo_logo, 1.90)
        listOfCategory.add(categoryModel)

        categoryModel = Category(2, "Food", R.drawable.kumo_logo, 1.90)
        listOfCategory.add(categoryModel)

        categoryModel = Category(3, "Technology", R.drawable.kumo_logo, 1.90)
        listOfCategory.add(categoryModel)

        categoryModel = Category(4, "News", R.drawable.kumo_logo, 1.90)
        listOfCategory.add(categoryModel)

        categoryModel = Category(5, "Economy", R.drawable.kumo_logo, 1.90)
        listOfCategory.add(categoryModel)

        categoryModel = Category(6, "Sports", R.drawable.kumo_logo, 1.90)
        listOfCategory.add(categoryModel)

        return listOfCategory
    }

    /*override fun onRecyclerViewItemClick(category: Category, position: Int)
    {
        lateinit var catClick: Intent
        if(category.id == 1)
        {
            catClick = Intent(this, ShowProducts::class.java)
        }

        startActivity(catClick)
    }*/

    override fun onNavigationItemSelected(item: MenuItem):Boolean {
        when (item.itemId) {
            R.id.nav_sign_out -> {
                Log.d("Testing logout ", "user")
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
        }

        return true
    }

}
