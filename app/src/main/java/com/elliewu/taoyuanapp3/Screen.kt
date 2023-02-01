package com.elliewu.taoyuanapp3

sealed class Screen(val route: String){
    object MA3_1 : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")
    object login :Screen("login")

    fun withArgs(vararg args: String): String{
        return buildString{
            append(route)
            args.forEach{ arg ->
                append("/$arg")
            }
        }
    }
}
