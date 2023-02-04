package com.elliewu.taoyuanapp3

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.Navigation
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.login.route) {
        composable(route = Screen.login.route) {
            //login(navController = navController)
            login{navController.navigate(Screen.MA3_1.route)}
        }
        composable(route = Screen.MA3_1.route) { entry ->
            MA3_1 { navController.navigate(Screen.login.route) }
            //BottomBtnCard (navController = navController, btnList = buttonBtn())
        }
        composable(route = Screen.MA3_2.route) { entry ->
            MA3_2 { navController.navigate(Screen.login.route) }
        }

    }
}



