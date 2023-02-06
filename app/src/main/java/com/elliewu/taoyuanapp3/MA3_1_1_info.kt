package com.elliewu.taoyuanapp3

import android.util.Size
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.component1
import androidx.core.graphics.component2
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


data class listInfo(
    val date: String,
    val classes: String,
    val roadLine: String,
    val workID:String,
    val worker:String,
    val state:String)
    var listInfoData = listOf(
        listInfo(
            "2022-04-06",
            "早班",
            "高鐵特定區",
            "111040601",
            "黃士昭",
            "執行中"
        ),
    )






@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Preview(showBackground = true)
@Composable
fun MA3_1_1_info(
   navController: NavHostController = rememberNavController()
) {
//    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(232, 232, 232)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(

            modifier = Modifier
                .fillMaxWidth()
                .size(width = 250.dp, 50.dp)
                .background(Color(62, 83, 140)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ClickableText(
                text = AnnotatedString("返回"),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.End,
                    textDecoration = TextDecoration.Underline,
                ),
                onClick = {
                    navController.navigate(Screen.MA3_1_1.route)
                },
                modifier = Modifier.padding(start = 20.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 40.dp),
                //size(width = 250.dp, height = 30.dp),

                text = "巡檢資訊",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color(255, 255, 255),
            )
        }
        Column(modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp)) {
            Text(
                text = "巡檢資訊",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color(85,85,85),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, bottom = 8.dp))

            //Box(){
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(255, 255, 255))) {
                    infoTable(listInfoData[0]);
                }
            //}
        }

    }
}

@Composable
fun infoTable(list: listInfo){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .bottomBorder(1.dp, Color(232,232,232)),
        )
        {
            Text(
                text = "日期",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                color = Color(128,127,129),
                modifier = Modifier.padding( bottom = 10.dp),
                                   //.fillMaxWidth(),
                textAlign = TextAlign.Start,
            )
           Row(modifier = Modifier.fillMaxWidth().padding(start = 90.dp), horizontalArrangement = Arrangement.Start)
           {
               Text(
                   text = list.date,
                   fontSize = 16.sp,
                   fontWeight = FontWeight.Bold,

                   color = Color(200, 71, 52),
                   modifier = Modifier.padding(bottom = 10.dp),
                   //.fillMaxWidth(),
                   textAlign = TextAlign.Center,
               )
           }
        }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .bottomBorder(1.dp, Color(232,232,232)),
    )
    {
        Text(
            text = "巡檢班次",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,

            color = Color(128,127,129),
            modifier = Modifier.padding( bottom = 10.dp),
            //.fillMaxWidth(),
            textAlign = TextAlign.Start,
        )
        Row(modifier = Modifier.fillMaxWidth().padding(start = 60.dp), horizontalArrangement = Arrangement.Start)
        {
            Text(
                text = list.classes,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                color = Color(200, 71, 52),
                modifier = Modifier.padding(bottom = 10.dp),
                //.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .bottomBorder(1.dp, Color(232,232,232)),
    )
    {
        Text(
            text = "巡檢路線",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,

            color = Color(128,127,129),
            modifier = Modifier.padding( bottom = 10.dp),
            //.fillMaxWidth(),
            textAlign = TextAlign.Start,
        )
        Row(modifier = Modifier.fillMaxWidth().padding(start = 60.dp), horizontalArrangement = Arrangement.Start)
        {
            Text(
                text = list.roadLine,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                color = Color(200, 71, 52),
                modifier = Modifier.padding(bottom = 10.dp),
                //.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .bottomBorder(1.dp, Color(232,232,232)),
    )
    {
        Text(
            text = "工單編號",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,

            color = Color(128,127,129),
            modifier = Modifier.padding( bottom = 10.dp),
            //.fillMaxWidth(),
            textAlign = TextAlign.Start,
        )
        Row(modifier = Modifier.fillMaxWidth().padding(start = 60.dp), horizontalArrangement = Arrangement.Start)
        {
            Text(
                text = list.workID,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                color = Color(200, 71, 52),
                modifier = Modifier.padding(bottom = 10.dp),
                //.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .bottomBorder(1.dp, Color(232,232,232)),
    )
    {
        Text(
            text = "巡檢人員",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,

            color = Color(128,127,129),
            modifier = Modifier.padding( bottom = 10.dp),
            //.fillMaxWidth(),
            textAlign = TextAlign.Start,
        )
        Row(modifier = Modifier.fillMaxWidth().padding(start = 60.dp), horizontalArrangement = Arrangement.Start)
        {
            Text(
                text = list.worker,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                color = Color(200, 71, 52),
                modifier = Modifier.padding(bottom = 10.dp),
                //.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .bottomBorder(1.dp, Color.Transparent),
    )
    {
        Text(
            text = "完成狀態",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,

            color = Color(128,127,129),
            modifier = Modifier.padding( bottom = 10.dp),
            //.fillMaxWidth(),
            textAlign = TextAlign.Start,
        )
        Row(modifier = Modifier.fillMaxWidth().padding(start = 60.dp), horizontalArrangement = Arrangement.Start)
        {
            Text(
                text = list.state,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                color = Color(200, 71, 52),
                modifier = Modifier.padding(bottom = 10.dp),
                //.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }
}
fun Modifier.bottomBorder(strokeWidth: Dp, color: Color) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = size.height - strokeWidthPx/2

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height),
                end = Offset(x = width , y = height),
                strokeWidth = strokeWidthPx
            )
        }
    }
)
