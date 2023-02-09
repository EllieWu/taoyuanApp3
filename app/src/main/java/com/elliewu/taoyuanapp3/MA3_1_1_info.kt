package com.elliewu.taoyuanapp3

import android.annotation.SuppressLint
import android.util.Log
import android.util.Size
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject


data class listInfo(
    val Date: String,
    val WorkTime: String,
    val WorkPath: String,
    val WorkCode:String,
    val UserName:String,
    val State:String
    )
var MA3_1_1_info_msggg by mutableStateOf(listInfo(
    "",
    "",
    "",
    "",
    "",
    ""
))





@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Preview(showBackground = true)
@Composable
fun MA3_1_1_info(
   navController: NavHostController = rememberNavController(),
   WorkCode: String? = "",
   WorkTime: String?=""
) {
    Log.d("Workcode","$WorkCode")
    MA3_1_1_Info_MakeListCom(WorkCode.toString());
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
            Icon(
                modifier = Modifier.padding(start = 5.dp).size(30.dp),
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "BackIcon",
                tint = Color.White
            )
            ClickableText(
                text = AnnotatedString("返回"),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.End,
                ),
                onClick = {
                    val MA3_1_1_fullRoutePath = Screen.MA3_1_1.route + "?WorkCode=${WorkCode}&WorkTime=${WorkTime}"
                    navController.navigate(MA3_1_1_fullRoutePath)
//                    navController.navigate(Screen.MA3_1_1.withArgs(WorkCode.toString()))
                    //navController.navigate(Screen.MA3_1_1.route)
                },
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
                    infoTable(MA3_1_1_info_msggg);
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
           Row(modifier = Modifier.fillMaxWidth().padding(start = 92.dp), horizontalArrangement = Arrangement.Start)
           {
               Text(
                   text = list.Date,
                   fontSize = 16.sp,
                   fontWeight = FontWeight.Bold,

                   color = Color(163,76,60),
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
                text = list.WorkTime,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                color = Color(163,76,60),
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
                text = list.WorkPath,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                color = Color(163,76,60),
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
                text = list.WorkCode,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                color = Color(163,76,60),
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
                text = list.UserName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,

                color = Color(163,76,60),
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
                text = list.State,
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

//TODO:Jeremy增加
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MA3_1_1_Info_MakeListCom(WorkCode:String){
    MA3_1_1_Info_MakeList(WorkCode)
}
fun MA3_1_1_Info_MakeList( WorkCode:String){
    GlobalScope.launch(Dispatchers.IO) {
        var RequestJsonObject = JSONObject();
        RequestJsonObject.put("Function", "WorkInfo")
        RequestJsonObject.put("WorkCode", WorkCode)
        val responseString = HttpRequestTest(RequestJsonObject)
        Log.d("MA3_1_1_Info",responseString)
        if(responseString!="Error"){
            var gson = Gson();
            var WorkInfoResponse:WorkInfo_Response = gson.fromJson(responseString,WorkInfo_Response::class.java)
            var workListDatas = MA3_1_1_info_msggg
            if(WorkInfoResponse.WorkCode != null){
                workListDatas = listInfo(
                    WorkInfoResponse.Date.toString(),
                    WorkInfoResponse.WorkTime.toString(),
                    WorkInfoResponse.WorkPath.toString(),
                    WorkInfoResponse.WorkCode.toString(),
                    WorkInfoResponse.UserName.toString(),
                    WorkInfoResponse.State.toString()
                )
            }
            MA3_1_1_info_msggg = workListDatas
        }
    }
}