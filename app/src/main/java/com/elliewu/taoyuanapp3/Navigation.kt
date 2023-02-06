package com.elliewu.taoyuanapp3

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.login.route) {
        composable(route = Screen.login.route) {
            login(navController = navController)
        }
        composable(route = Screen.MA3_1.route) { entry ->
            MA3_1(navController = navController)
        }
        composable(route = Screen.MA3_2.route) { entry ->
            MA3_2(navController = navController)
        }
        composable(route = Screen.MA3_3.route) { entry ->
            MA3_3(navController = navController)
        }
        composable(route = Screen.changePassword.route) { entry ->
            changePassword(navController = navController)
        }
        composable(route = Screen.changePassword_newPassword.route) { entry ->
            changePassword_newPassword(navController = navController)
        }
        composable(route = Screen.MA3_1_1.route) { entry ->
            MA3_1_1(navController = navController)
        }
        composable(route = Screen.MA3_1_1_info.route) { entry ->
            MA3_1_1_info(navController = navController)
        }


    }


}



