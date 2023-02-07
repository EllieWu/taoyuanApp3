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
//        composable(route = Screen.MA3_1_1.route) { entry ->
//            MA3_1_1(navController = navController)
//        }
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
//        composable(route = Screen.MA3_1_1_info.route) { entry ->
//            MA3_1_1_info(navController = navController)
//        }
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


    }


}

//@Composable
//fun MA3_1_1(WorkCode: String?,navController: NavHostController = rememberNavController()){
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight()
//            .background(Color(232, 232, 232)),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .size(width = 250.dp, 50.dp)
//                .background(Color(62, 83, 140)),
//            verticalAlignment = Alignment.CenterVertically,
//        ) {
//            ClickableText(
//                text = AnnotatedString("返回"),
//                style = TextStyle(
//                    color = Color.White,
//                    fontSize = 20.sp,
//                    textAlign = TextAlign.End,
//                    textDecoration = TextDecoration.Underline,
//                ),
//                onClick = {
//                    navController.navigate(Screen.MA3_1.route)
//                },
//                modifier = Modifier.padding(start = 20.dp)
//            )
//            Text(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(1f)
//                    .padding(end = 40.dp),
//                //size(width = 250.dp, height = 30.dp),
//
//                text = "巡檢打卡填報",
//                fontSize = 20.sp,
//                textAlign = TextAlign.Center,
//                color = Color(255, 255, 255),
//            )
//
//
//        }
//        Column(modifier = Modifier.fillMaxSize()) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 10.dp)
//            ) {
//                //infoLayout(navController)
//                Text(text = "yoyo, $WorkCode")
//            }
//            BottomSpace2();
//        }
//    }
//}

