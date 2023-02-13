package com.elliewu.taoyuanapp3

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
data class buttonBtn(val icon: Int, val text: String, val screen: Screen,val number: Int)
var nowclickedpage by mutableStateOf(1)
var buttonBtnData = listOf(
    buttonBtn(
        R.drawable.list, "巡檢工單", Screen.MA3_1,1
    ),
    buttonBtn(
        R.drawable.p2, "維修工單", Screen.MA3_2,2
    ),
    buttonBtn(
        R.drawable.p3, "報修", Screen.MA3_3,3
    ),
    buttonBtn(
        R.drawable.p4, "密碼變更", Screen.changePassword,4
    ),
)

@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Preview(showBackground = true)
@Composable
fun BottomSpace(navController :NavHostController = rememberNavController()) {
    Box(modifier = Modifier.fillMaxSize(), Alignment.BottomCenter) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .size(width = 1000.dp, height = 80.dp)
                .fillMaxHeight()
                .background(Color(253, 253, 253)),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
//            var selected by remember { mutableStateOf(false) }
//            val clickColor =
//                if(selected){
//                    (Color(255, 248, 220))
//                }else {
//                    (Color(157, 157, 157))
//                }
//             點擊背景顏色      Color(229,221,198)
//             點擊圖案、文字顏色 Color(247,240,213)
//            val selectedBG = if (title == "外巡巡檢工單")
//            {
//                Color(255,248,220)
//            }else{
//                Color(254,166,0)
//            }
//
//            val selectColor = if (title == "外巡巡檢工單")
//            {
//                Color(235,166,59)
//            }else{
//                Color(157,157,157)
//            }
            BottomBtn(buttonBtnData,navController);
        }
    }
}

@Composable
fun BottomBtnCard(btnList: buttonBtn,clicked:Color,navController :NavHostController = rememberNavController()) {
    if(btnList.number == nowclickedpage)
    {
        Button(
            border = BorderStroke(0.dp, Color.Transparent),
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent
            ),
            modifier = Modifier.size(width = 100.dp, 80.dp),
            onClick = {
                nowclickedpage = btnList.number;
                Log.d("ButtonEvent", btnList.screen.route.toString())
                navController.navigate(btnList.screen.route)

            }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painterResource(id = btnList.icon),
                    contentDescription = "",
                    modifier = Modifier.size(30.dp),
                    colorFilter = ColorFilter.tint(clicked)
                )
                Text(
                    text = btnList.text,
                    color = clicked,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                )
            }

        }
    }
    else
    {
        Button(
            border = BorderStroke(0.dp, Color.Transparent),
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent
            ),
            modifier = Modifier.size(width = 100.dp, 80.dp),
            onClick = {
                nowclickedpage = btnList.number;
                Log.d("ButtonEvent", btnList.screen.route.toString())
                navController.navigate(btnList.screen.route)

            }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painterResource(id = btnList.icon),
                    contentDescription = "",
                    modifier = Modifier.size(30.dp),
                    colorFilter = ColorFilter.tint(Color(157, 157, 157))
                )
                Text(
                    text = btnList.text,
                    color = Color(157, 157, 157),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                )
            }

        }
    }

}


@Composable
fun BottomBtn(messages: List<buttonBtn>,navController :NavHostController = rememberNavController()) {
    LazyRow(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceAround) {
        items(messages) { message ->
            BottomBtnCard(message,Color(235, 166, 59),navController)
        }
    }
}

//@Preview
//@Composable
//fun p(){
//    Column() {
//        BottomBtnCard(buttonBtnData[0],Color(235, 166, 59))
//    }
//}