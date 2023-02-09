package com.elliewu.taoyuanapp3

sealed class Screen(val route: String){
    object login : Screen("login")

    //巡檢工單
    object MA3_1 : Screen("MA3_1")
    object MA3_1_1 : Screen("MA3_3_1")
    object MA3_1_1_info : Screen("MA3_3_1_info")

    //維修工單
    object MA3_2 : Screen("MA3_2")
    object MA3_2_1 : Screen("MA3_2_1")
    object MA3_2_1_finishRepair : Screen("MA3_2_1_finishRepair")


    //報修
    object MA3_3 : Screen("MA3_3")
    object MA3_3_1 : Screen("MA3_3_1")

    object MA3_3_NEW1 :Screen("MA3_3_NEW1")
    //變更密碼
    object changePassword : Screen("changePassword")
    object changePassword_newPassword : Screen("changePassword_newPassword")



    //相機
    object CameraTest : Screen("CameraTest")




    fun withArgs(vararg args: String): String{
        return buildString{
            append(route)
            args.forEach{ arg ->
                append("/$arg")
            }
        }
    }
}
