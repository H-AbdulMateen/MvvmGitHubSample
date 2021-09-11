package com.iislex.mvvmgithubsample.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.iislex.mvvmgithubsample.R
import com.iislex.mvvmgithubsample.presentation.components.GenericDialog
import com.iislex.mvvmgithubsample.presentation.ui.theme.MvvmGitHubSampleTheme
import com.iislex.mvvmgithubsample.presentation.ui.theme.ProcessDialogQueue
import com.iislex.mvvmgithubsample.presentation.ui.util.ConnectivityManager
import com.iislex.mvvmgithubsample.presentation.ui.util.DialogQueue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainBody(viewModel, connectivityManager)
        }
    }

}

@Composable
fun MainBody(viewModel: MainViewModel, connectivityManager: ConnectivityManager){
    val context = LocalContext.current
    val loading = viewModel.loading.value
    val dialogQueue = viewModel.dialogQueue.queue.value
    val scaffoldState = rememberScaffoldState()
    val username = viewModel.username.value
    val userInfo = viewModel.userInfo.value
    val isNetworkAvailable = connectivityManager.isNetworkAvailable.value
    MvvmGitHubSampleTheme(
        displayProgressBar = loading,
        scaffoldState = scaffoldState,
        dialogQueue = dialogQueue,
        isNetworkAvailable = isNetworkAvailable){
        Box(modifier = Modifier.background(MaterialTheme.colors.surface)){
            Column(modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.github), contentDescription = null,
                modifier = Modifier.size(80.dp))
                OutlinedTextField(
                    value = username,
                    onValueChange = viewModel::onUsernameChange,
                placeholder = { Text(text = stringResource(id = R.string.enter_username)) } ,
                label = { Text(text = stringResource(id = R.string.enter_username)) } ,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth())
                Button(
                    onClick = {
                        if (isNetworkAvailable) {
                            viewModel.onTriggerEvent(
                                UserInfoEvent.GetUserInfoEvent
                            )
                        }else{
                        Toast.makeText(context, "No network Available", Toast.LENGTH_LONG).show()
                        }
                    }){
                    Text(text = stringResource(id = R.string.search))
                }
                if (!loading && userInfo != null){
                    Text(text = "User: $userInfo")
                }

            }
        }
    }
}
