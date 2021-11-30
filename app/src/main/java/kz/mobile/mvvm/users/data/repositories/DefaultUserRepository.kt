package kz.mobile.mvvm.users.data.repositories

import kz.mobile.mvvm.users.data.api.FakeUserDao
import kz.mobile.mvvm.users.data.mappers.UserMapper
import kz.mobile.mvvm.users.data.models.UserResponse
import kz.mobile.mvvm.users.domain.mappers.UserResponseMapper
import kz.mobile.mvvm.users.domain.models.User
import kz.mobile.mvvm.users.domain.repositories.UserRepository

class DefaultUserRepository(
    private val userDao: FakeUserDao,
    private val userMapper: UserMapper = UserMapper(),
    private val userResponseMapper: UserResponseMapper = UserResponseMapper()
) : UserRepository {

    override suspend fun addUser(user: User) {
        val userResponse: UserResponse = userResponseMapper.map(user)
        userDao.addUser(userResponse)
    }

    override suspend fun getUsers(): List<User> {
        val usersResponse: List<UserResponse> = userDao.getUsers()
        val users: MutableList<User> = mutableListOf()
        for(item in usersResponse){
            val user: User = userMapper.map(item)
            users.add(user)
        }
        return users
    }
}