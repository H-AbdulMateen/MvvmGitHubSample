package com.iislex.mvvmgithubsample.repository

import com.iislex.mvvmgithubsample.domain.data.DataState
import com.iislex.mvvmgithubsample.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
     fun execute(username: String): Flow<DataState<User>>
}