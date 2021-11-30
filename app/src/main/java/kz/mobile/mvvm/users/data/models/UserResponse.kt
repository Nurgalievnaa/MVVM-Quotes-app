package kz.mobile.mvvm.users.data.models

import android.net.Uri

data class UserResponse(
    val name: String,
    val surname: String,
    val image: Uri
) {

    override fun toString(): String {
        return "$name $surname $image"
    }
}