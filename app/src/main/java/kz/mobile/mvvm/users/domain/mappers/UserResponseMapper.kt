package kz.mobile.mvvm.users.domain.mappers

import kz.mobile.mvvm.users.data.models.UserResponse
import kz.mobile.mvvm.users.domain.models.User


class UserResponseMapper {
    fun map(user: User): UserResponse {
        return UserResponse(
            name = user.name,
            surname = user.surname,
            image = user.image
        )
    }
}