package com.movieapp.authentication.di

import com.movieapp.authentication.repository.AccountRepository
import com.movieapp.authentication.repository.AccountRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AccountModule {
    @Binds
    abstract fun bindRepository(repo: AccountRepositoryImpl): AccountRepository
}