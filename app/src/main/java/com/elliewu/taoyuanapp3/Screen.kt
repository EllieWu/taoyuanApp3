package com.elliewu.taoyuanapp3

sealed class Screen(val route: String){
    object login : Screen("login")
    object MA3_1 : Screen("MA3_1")
    object MA3_2 : Screen("MA3_2")


//    fun withArgs(vararg args: String): String{
//        return buildString{
//            append(route)
//            args.forEach{ arg ->
//                append("/$arg")
//            }
//        }
//    }
}
