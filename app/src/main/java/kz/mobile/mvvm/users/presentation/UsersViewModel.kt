package kz.mobile.mvvm.users.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.mobile.mvvm.users.domain.models.User
import kz.mobile.mvvm.users.domain.repositories.UserRepository

class UsersViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val userLiveData = MutableLiveData<List<User>>()

    fun getUsersLiveData(): LiveData<List<User>> = userLiveData

    fun getUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            val usersResponse: List<User> = userRepository.getUsers()
            withContext(Dispatchers.Main) {
                userLiveData.value = usersResponse
            }
        }
    }

    fun addUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userRepository.addUser(user)
        }
    }
}