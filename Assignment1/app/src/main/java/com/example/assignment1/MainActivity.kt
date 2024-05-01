package com.example.assignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    @RequiresApi(64)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainNavigation()
        }
    }
}

@RequiresApi(64)
@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginPage(navController) }
        composable("signup") { SignUpPage(navController) }
    }
}

@RequiresApi(64)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    SignUpPage(navController)
}