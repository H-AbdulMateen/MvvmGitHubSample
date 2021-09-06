package com.iislex.mvvmgithubsample.presentation.ui.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.iislex.mvvmgithubsample.presentation.components.GenericDialogInfo
import com.iislex.mvvmgithubsample.presentation.components.PositiveAction
import java.util.*

class DialogQueue {
    // Queue for "First-In-First-Out" behavior
    val queue: MutableState<Queue<GenericDialogInfo>> = mutableStateOf(LinkedList())

    fun removeHeadMessage(){
        if(queue.value.isNotEmpty()){
            val update = queue.value
            update.remove()
            queue.value = ArrayDeque()
            queue.value = update
        }
    }

    fun appendErrorMessage(title: String, description: String){
        queue.value.offer(
            GenericDialogInfo.Builder()
                .title(title)
                .onDismiss(this::removeHeadMessage)
                .description(description)
                .positive(
                    PositiveAction(
                        positiveBtnTxt = "Ok",
                        onPositiveAction = this::removeHeadMessage,
                    )
                )
                .build()
        )
    }

}