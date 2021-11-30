package kz.mobile.mvvm.users.di

import kz.mobile.mvvm.users.data.api.FakeUserDatabase
import kz.mobile.mvvm.users.data.repositories.DefaultUserRepository
import kz.mobile.mvvm.users.domain.repositories.UserRepository
import kz.mobile.mvvm.users.presentation.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val userModules: Module = module {
    viewModel {
        UsersViewModel(
            userRepository = get()
        )
    }
    factory {
        DefaultUserRepository(
            userDao = FakeUserDatabase.getInstance().userDao
        ) as UserRepository
    }
}