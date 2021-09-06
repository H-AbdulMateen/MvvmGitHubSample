package com.iislex.mvvmgithubsample.repository

import android.util.Log
import com.iislex.mvvmgithubsample.domain.data.DataState
import com.iislex.mvvmgithubsample.domain.model.User
import com.iislex.mvvmgithubsample.network.RetroService
import com.iislex.mvvmgithubsample.network.model.UserDto
import com.iislex.mvvmgithubsample.network.model.UserDtoMapper
import com.iislex.mvvmgithubsample.utils.APP_DEBUG
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository_Impl(
    private val retroService: RetroService,
    private val dtoMapper: UserDtoMapper
): UserRepository {
       override fun execute(username: String): Flow<DataState<User>> = flow {
            try {
                emit(DataState.loading())
                delay(1000)
                val user = requestUserInfo(username)
                emit(DataState.success<User>(dtoMapper.mapToDomainModel(user)))
            }catch (ex: Exception){
                Log.d(APP_DEBUG, "UserRepo: ${ex.cause}")
                emit(DataState.error<User>("Error: ${ex.cause}"))
            }
        }

    private suspend fun requestUserInfo(username: String): UserDto {
        return retroService.getUseInfo(username = username)
    }
}