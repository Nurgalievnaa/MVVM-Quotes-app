package kz.mobile.mvvm.users.data.api


class FakeUserDatabase() {

    var userDao = FakeUserDao()

    companion object {
        @Volatile private var instance: FakeUserDatabase? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: FakeUserDatabase().also { instance = it }
            }
    }
}