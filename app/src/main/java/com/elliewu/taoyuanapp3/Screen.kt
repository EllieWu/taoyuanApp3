package com.elliewu.taoyuanapp3

sealed class Screen(val route: String){
    object login : Screen("login")
    object MA3_1 : Screen("MA3_1")
    object MA3_2 : Screen("MA3_2")
    object MA3_3 : Screen("MA3_3")
    object changePassword : Screen("changePassword")
    object changePassword_newPassword : Screen("changePassword_newPassword")
    object MA3_1_1 : Screen("MA3_3_1")
    object MA3_1_1_info : Screen("MA3_3_1_info")



//    fun withArgs(vararg args: String): String{
//        return buildString{
//            append(route)
//            args.forEach{ arg ->
//                append("/$arg")
//            }
//        }
//    }
}
