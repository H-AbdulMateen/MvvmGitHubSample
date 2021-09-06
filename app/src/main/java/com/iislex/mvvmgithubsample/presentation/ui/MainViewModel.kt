package com.iislex.mvvmgithubsample.presentation.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iislex.mvvmgithubsample.domain.model.User
import com.iislex.mvvmgithubsample.presentation.ui.util.DialogQueue
import com.iislex.mvvmgithubsample.repository.UserRepository
import com.iislex.mvvmgithubsample.utils.APP_DEBUG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: UserRepository): ViewModel() {
    val loading = mutableStateOf(false)
    val userInfo: MutableState<User?> = mutableStateOf(null)
    val dialogQueue = DialogQueue()
    val username = mutableStateOf("")


    fun onUsernameChange(name: String){
        username.value = name
    }

    fun onTriggerEvent(event: UserInfoEvent){
        when(event){
            is UserInfoEvent.GetUserInfoEvent -> {
                checkUserInf()
            }
        }
    }
   private fun checkUserInf(){
                repository.execute(username = username.value).onEach {dataState ->
                    loading.value = dataState.loading
                    dataState.data?.let {user ->
                    userInfo.value = user
                        Log.d(APP_DEBUG, "ViewModel: $user")
                    }
                    dataState.error?.let { error ->
                        Log.d(APP_DEBUG, "ViewModel: $error")
                        dialogQueue.appendErrorMessage("An Error occurred: ", error)
                    }
                }.launchIn(viewModelScope)


    }
}