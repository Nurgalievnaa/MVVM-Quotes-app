package kz.mobile.mvvm.users.domain.models

import android.net.Uri

data class User(
    val name: String,
    val surname: String,
    val image: Uri
)