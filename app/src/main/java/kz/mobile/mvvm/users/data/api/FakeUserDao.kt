package kz.mobile.mvvm.users.data.api

import kz.mobile.mvvm.users.data.models.UserResponse

class FakeUserDao {
    private var userList = mutableListOf<UserResponse>()

    fun addUser(userResponse: UserResponse) {
        userList.add(userResponse)
    }

    fun getUsers() = userList as List<UserResponse>
}