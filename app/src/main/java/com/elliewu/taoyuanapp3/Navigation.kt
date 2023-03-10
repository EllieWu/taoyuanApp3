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
import com.google.android.gms.location.FusedLocationProviderClient

@Composable
fun Navigation(onBackPressed:()->Unit = {},viewModel:MapViewModel,fusedLocationProviderClient: FusedLocationProviderClient) {
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
            route = Screen.MA3_1_1.route + "?WorkCode={WorkCode}&WorkTime={WorkTime}",
            arguments = listOf(
                navArgument("WorkCode"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("WorkTime"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { entry ->
            MA3_1_1(
                navController = navController,
                WorkCode = entry.arguments?.getString("WorkCode"),
                WorkTime = entry.arguments?.getString("WorkTime"),
                state = viewModel.state.value,
                setupClusterManager = viewModel::setupClusterManager,
                calculateZoneViewCenter = viewModel::calculateZoneLatLngBounds,
                fusedLocationProviderClient = fusedLocationProviderClient,
                viewModel = viewModel
            )
        }
        composable(
            route = Screen.MA3_1_1_info.route + "?WorkCode={WorkCode}&WorkTime={WorkTime}",
            arguments = listOf(
                navArgument("WorkCode"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("WorkTime"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }

            )
        ) { entry ->
            MA3_1_1_info(
                navController = navController,
                WorkCode = entry.arguments?.getString("WorkCode"),
                WorkTime = entry.arguments?.getString("WorkTime"),
            )
        }

//        composable(route = Screen.MA3_1_1_Buttonbtn1.route) { entry ->
//            MA3_1_1_Buttonbtn1(navController = navController)
//        }
        composable(
            route = Screen.MA3_1_1_Buttonbtn1.route + "?WorkTime={WorkTime}&WorkCode={WorkCode}&Longitude={Longitude}&Latitude={Latitude}",
            arguments = listOf(
                navArgument("WorkCode"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("WorkTime"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("Longitude"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("Latitude"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }

            )
        ) { entry ->
            MA3_1_1_Buttonbtn1(
                navController = navController,
                WorkTime = entry.arguments?.getString("WorkTime"),
                WorkCode = entry.arguments?.getString("WorkCode"),
                Longitude = entry.arguments?.getString("Longitude"),
                Latitude = entry.arguments?.getString("Latitude")
            )
        }

//        composable(route = Screen.MA3_1_1_Bottombtn2.route) { entry ->
//            MA3_1_1_Bottombtn2(navController = navController)
//        }
        composable(
            route = Screen.MA3_1_1_Bottombtn2.route + "?WorkCode={WorkCode}&WorkTime={WorkTime}",
            arguments = listOf(
                navArgument("WorkCode"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("WorkTime"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { entry ->
            MA3_1_1_Bottombtn2(
                navController = navController,
                WorkCode = entry.arguments?.getString(("WorkCode")),
                WorkTime = entry.arguments?.getString(("WorkTime"))

            )
        }

//        composable(route = Screen.MA3_1_1_Bottombtn3.route) { entry ->
//            MA3_1_1_Bottombtn3(navController = navController)
//        }
//        composable(
//            route = Screen.MA3_1_1_Bottombtn3.route + "?WorkCode={WorkCode}&WorkTime={WorkTime}",
//            arguments = listOf(
//                navArgument("WorkCode"){
//                    type = NavType.StringType
//                    defaultValue = ""
//                    nullable = true
//                },
//                navArgument("WorkTime"){
//                    type = NavType.StringType
//                    defaultValue = ""
//                    nullable = true
//                }
//            )
//        ) { entry ->
//            MA3_1_1_Bottombtn3(
//                navController = navController,
//                WorkCode = entry.arguments?.getString(("WorkCode")),
//                WorkTime = entry.arguments?.getString(("WorkTime"))
//
//            )
//        }
        composable(
            route = Screen.MA3_1_1_Bottombtn3.route + "?WorkCode={WorkCode}&WorkTime={WorkTime}",
            arguments = listOf(
                navArgument("WorkCode"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("WorkTime"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { entry ->
            MA3_1_1_Bottombtn3(
                navController = navController,
                WorkCode = entry.arguments?.getString("WorkCode"),
                WorkTime = entry.arguments?.getString("WorkTime"),
                state = viewModel.state.value,
                fusedLocationProviderClient = fusedLocationProviderClient,
                viewModel = viewModel
            )
        }
        //報修點預覽
        composable(route =  Screen.MA3_1_1_RepairDotPreview.route + "?Longitude={Longitude}&Latitude={Latitude}&WorkCode={WorkCode}&WorkTime={WorkTime}",
            arguments = listOf(
                navArgument("Longitude"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("Latitude"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("WorkCode"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("WorkTime"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        )
        {
            entry ->
            MA3_1_1_RepairDotPreview(
                navController = navController,
                Latitude = entry.arguments?.getString("Latitude"),
                Longitude = entry.arguments?.getString("Longitude"),
                WorkCode = entry.arguments?.getString("WorkCode"),
                WorkTime = entry.arguments?.getString("WorkTime"),
                state = viewModel.state.value,
                fusedLocationProviderClient = fusedLocationProviderClient,
                viewModel = viewModel
            )
        }
        composable(route =  Screen.MA3_1_1_WorkPoint.route + "?Longitude={Longitude}&Latitude={Latitude}&WorkCode={WorkCode}&WorkTime={WorkTime}&LocateNumber={LocateNumber}",
            arguments = listOf(
                navArgument("Longitude"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("Latitude"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("WorkCode"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("WorkTime"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("LocateNumber"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        )
        {
                entry ->
            MA3_1_1_WorkPoint(
                navController = navController,
                Latitude = entry.arguments?.getString("Latitude"),
                Longitude = entry.arguments?.getString("Longitude"),
                WorkCode = entry.arguments?.getString("WorkCode"),
                WorkTime = entry.arguments?.getString("WorkTime"),
                LocateNumber = entry.arguments?.getString("LocateNumber"),
            )
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

        composable(route = Screen.MA3_2_1_finishRepair.route) { entry ->
            MA3_2_1_finishRepair(navController = navController)
        }

        composable(
            route = Screen.MA3_2_2.route + "?longitude={longitude}&latitude={latitude}&repairTitle={repairTitle}",
            arguments = listOf(
                navArgument("longitude"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("latitude"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },navArgument("repairTitle"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { entry ->
            MA3_2_2(
                navController = navController,
                latitude = entry.arguments?.getString("latitude"),
                longitude = entry.arguments?.getString("longitude"),
                repairTitle = entry.arguments?.getString("repairTitle"),
                state = viewModel.state.value,
                fusedLocationProviderClient = fusedLocationProviderClient,
                viewModel = viewModel,
            )
        }





        //報修
        composable(route = Screen.MA3_3.route) { entry ->
            MA3_3(navController = navController)
        }
//        composable(route = Screen.MA3_3_test.route) { entry ->
//            MA3_3_1(navController = navController)
//        }
        composable(route = Screen.MA3_3_NEW1.route) { entry ->
            MA3_3_NEW1(navController = navController)
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

        //維修點預覽
        composable(route = Screen.MA3_3_1_RepairDotPreview.route + "?longitude={longitude}&latitude={latitude}",
            arguments = listOf(
                navArgument("longitude"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("latitude"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        )
        { entry ->
            MA3_3_1_RepairDotPreview(
                navController = navController,
                latitude = entry.arguments?.getString("latitude"),
                longitude = entry.arguments?.getString("longitude"),
                state = viewModel.state.value,
                fusedLocationProviderClient = fusedLocationProviderClient,
                viewModel = viewModel
            )
        }



        //變更密碼
        composable(route = Screen.changePassword.route) { entry ->
            changePassword(navController = navController)
        }
        composable(route = Screen.changePassword_newPassword.route) { entry ->
            changePassword_newPassword(navController = navController)
        }



        //相機
        composable(route = Screen.CameraTest.route) { entry ->
            CameraTest(onBackPressed,navController = navController)
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

