package com.elliewu.taoyuanapp3

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class buttonBtn2(val icon: Int, val text: String,val screen: Screen)

var buttonBtnData2 = listOf(
    buttonBtn2(
        R.drawable.btn1, "巡檢打卡",Screen.MA3_1
    ),
    buttonBtn2(
        R.drawable.btn2, "完工填報",Screen.MA3_2
    ),
    buttonBtn2(
        R.drawable.btn3, "報修",Screen.MA3_3
    ),
)

@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Preview(showBackground = true)



@Composable
fun BottomSpace2() {
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
            BottomBtn2(buttonBtnData2);
        }
    }
}

@Composable
fun BottomBtnCard2(btnList: buttonBtn2) {
    val bgColor =
        if (btnList.text == "巡檢打卡") {
            Color(235, 166, 59)
        } else if (btnList.text == "完工填報") {
            Color(235, 166, 59)
        } else if (btnList.text == "報修") {
            Color(70, 118, 165)
        } else {
            Color(157, 157, 157)
        }
    Button(
        border = BorderStroke(0.dp, Color.Transparent),
        elevation = null,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = bgColor
        ),
        modifier = Modifier.size(width = 100.dp, 80.dp),
        onClick = {


//                val screen = btnList.screen
//                navController.navigate(screen.route)

//            println(value);
//

        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painterResource(id = btnList.icon),
                contentDescription = "",
                modifier = Modifier.size(30.dp).padding(bottom = 5.dp),
                colorFilter = ColorFilter.tint(Color(255,255,255))
            )
            Text(
                text = btnList.text,
                color = Color(255,255,255),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
            )
        }

    }
}

@Composable
fun info(){

}

@Composable
fun BottomBtn2(messages: List<buttonBtn2>) {
    LazyRow(modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp, vertical = 5.dp),horizontalArrangement = Arrangement.SpaceAround) {
        items(messages) { message ->
            BottomBtnCard2(message)
        }
    }
}