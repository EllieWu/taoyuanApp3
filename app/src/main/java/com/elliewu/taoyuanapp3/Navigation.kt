package com.elliewu.taoyuanapp3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
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

        //外巡工單
        composable(route = Screen.MA3_1.route) { entry ->
            MA3_1(navController = navController)
        }
        composable(
            route = Screen.MA3_1_1.route + "/{WorkCode}",
            arguments = listOf(
                navArgument("WorkCode"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { entry ->
            MA3_1_1(
                navController = navController,
                WorkCode = entry.arguments?.getString("WorkCode"))
        }
        composable(
            route = Screen.MA3_1_1_info.route + "/{WorkCode}",
            arguments = listOf(
                navArgument("WorkCode"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { entry ->
            MA3_1_1_info(
                navController = navController,
                WorkCode = entry.arguments?.getString("WorkCode"))
        }

        //維修工單
        composable(route = Screen.MA3_2.route) { entry ->
            MA3_2(navController = navController)
        }
//        composable(route = Screen.MA3_2_1.route) { entry ->
//            MA3_2_1(navController = navController)
//        }
        composable(
            route = Screen.MA3_2_1.route + "?RepairCode={RepairCode}&State={State}",
            arguments = listOf(
                navArgument("RepairCode"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("State"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { entry ->
            MA3_2_1(
                navController = navController,
                RepairCode = entry.arguments?.getString("RepairCode"),
                State = entry.arguments?.getString("State")
            )
        }


        //報修
        composable(route = Screen.MA3_3.route) { entry ->
            MA3_3(navController = navController)
        }
        composable(route = Screen.MA3_3_1.route) { entry ->
            MA3_3_1(navController = navController)
        }
//        composable(
//            route = Screen.MA3_3_1.route + "/{ReportCode}",
//            arguments = listOf(
//                navArgument("ReportCode"){
//                    type = NavType.StringType
//                    defaultValue = ""
//                    nullable = true
//                }
//            )
//        ) { entry ->
//            MA3_3_1(
//                navController = navController,
//                ReportCode = entry.arguments?.getString("ReportCode"))
//        }




        //變更密碼
        composable(route = Screen.changePassword.route) { entry ->
            changePassword(navController = navController)
        }
        composable(route = Screen.changePassword_newPassword.route) { entry ->
            changePassword_newPassword(navController = navController)
        }



        //相機
        composable(route = Screen.CameraTest.route) { entry ->
            CameraTest(navController = navController)
        }
//        composable(
//            route = Screen.MA3_2_1.route + "/{RepairCode}",
//            arguments = listOf(
//                navArgument("RepairCode"){
//                    type = NavType.StringType
//                    defaultValue = ""
//                    nullable = true
//                }
//            )
//        ) { entry ->
//            MA3_2_1(
//                navController = navController,
//                RepairCode = entry.arguments?.getString(("RepairCode"))
//                )
//        }
    }


}

