package com.melbpc.mohankumargupta.helloworldtv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
//import androidx.tv.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
//import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.melbpc.mohankumargupta.helloworldtv.navigation.NavigationRoot
import com.melbpc.mohankumargupta.helloworldtv.ui.theme.HelloWorldTVTheme

class MainActivity : ComponentActivity() {
    //@OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorldTVTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    //shape = RectangleShape

                ) {
                    Greeting("your mama")
                    //NavigationRoot()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    NavigationRoot()
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloWorldTVTheme {
        Greeting("your mama preview")
    }
}