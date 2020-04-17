package com.example.kumoapp2.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.kumoapp2.Model.User
import com.example.kumoapp2.Repository.UserRepository
import com.example.kumoapp2.Utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class SignUpActivityViewModel(private val userRepository: UserRepository) : ViewModel()
{
    /*private val repo: UserRepository = UserRepository()

    val data: LiveData<User> = liveData(Dispatchers.IO) {
        val retreivedData = repo.registerUser(user)
        emit(retreivedData)
    }*/

    fun registerUser(user: User) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = userRepository.registerUser(user)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }

    }

}