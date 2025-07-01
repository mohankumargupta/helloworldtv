package com.melbpc.mohankumargupta.helloworldtv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.tv.material3.Surface
import com.melbpc.mohankumargupta.helloworldtv.navigation.NavigationRoot
import com.melbpc.mohankumargupta.helloworldtv.ui.theme.HelloWorldTVTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorldTVTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    NavigationRoot(viewModel)
                }
            }
        }
    }
}

