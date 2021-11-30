package kz.mobile.mvvm.users.data.mappers

import kz.mobile.mvvm.users.data.models.UserResponse
import kz.mobile.mvvm.users.domain.models.User

class UserMapper {
    fun map(userResponse: UserResponse): User {
        return User(
            name = userResponse.name,
            surname = userResponse.surname,
            image = userResponse.image
        )
    }
}