package kz.mobile.mvvm.users.domain.repositories

import kz.mobile.mvvm.users.domain.models.User

interface UserRepository {

    suspend fun getUsers(): List<User>

    suspend fun addUser(user: User)
}