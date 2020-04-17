package com.example.kumoapp2.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kumoapp2.Repository.ApiHelper
import com.example.kumoapp2.Repository.UserRepository
import com.example.kumoapp2.ViewModel.SignUpActivityViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create (modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpActivityViewModel::class.java)) {
            return SignUpActivityViewModel(UserRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}